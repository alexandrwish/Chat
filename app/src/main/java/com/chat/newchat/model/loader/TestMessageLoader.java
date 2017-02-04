package com.chat.newchat.model.loader;

import com.chat.newchat.ChatApplication;
import com.chat.newchat.common.MessageComparator;
import com.chat.newchat.model.entity.MessageEntity;
import com.chat.newchat.model.entity.MessageEntityDao;
import com.chat.newchat.model.entity.UserEntity;
import com.chat.newchat.model.entity.UserEntityDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import it.slyce.messaging.message.MessageSource;
import it.slyce.messaging.message.TextMessage;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class TestMessageLoader implements MessageLoader {

    private static final int UPDATE_INTERVAL = 5;
    private static final int MAX_MESSAGES_SIZE = 50;
    private static final String[] LATIN = {
            "Vestibulum dignissim enim a mauris malesuada fermentum. Vivamus tristique consequat turpis, pellentesque.",
            "Quisque nulla leo, venenatis ut augue nec, dictum gravida nibh. Donec augue nisi, volutpat nec libero.",
            "Cras varius risus a magna egestas.",
            "Mauris tristique est eget massa mattis iaculis. Aenean sed purus tempus, vestibulum ante eget, vulputate mi. Pellentesque hendrerit luctus tempus. Cras feugiat orci.",
            "Morbi ullamcorper, sapien mattis viverra facilisis, nisi urna sagittis nisi, at luctus lectus elit.",
            "Phasellus porttitor fermentum neque. In semper, libero id mollis.",
            "Praesent fermentum hendrerit leo, ac rutrum ipsum vestibulum at. Curabitur pellentesque augue.",
            "Mauris finibus mi commodo molestie placerat. Curabitur aliquam metus vitae erat vehicula ultricies. Sed non quam nunc.",
            "Praesent vel velit at turpis vestibulum eleifend ac vehicula leo. Nunc lacinia tellus eget ipsum consequat fermentum. Nam purus erat, mollis sed ullamcorper nec, efficitur.",
            "Suspendisse volutpat enim eros, et."
    };

    private static int n;

    private String getRandomMessage() {
        return n++ + ": " + LATIN[(int) (Math.random() * 10)];
    }

    public Observable<List<TextMessage>> loadAll() {
        return Observable.concat(getDBMessages(), getTestMessages());
    }

    public void save(String text) {
        clearOld();
        List<UserEntity> users = ChatApplication.getInstance().getSession().getUserEntityDao().queryBuilder().where(UserEntityDao.Properties.Uuid.eq("my")).list();
        if (users.size() > 0) {
            MessageEntity entity = new MessageEntity(null, text, users.get(0).getId(), System.currentTimeMillis());
            ChatApplication.getInstance().getSession().getMessageEntityDao().insert(entity);
        }
    }

    private void clearOld() {
        MessageEntityDao dao = ChatApplication.getInstance().getSession().getMessageEntityDao();
        List<MessageEntity> messages = dao.loadAll();
        if (messages.size() >= MAX_MESSAGES_SIZE) {
            Collections.sort(messages, MessageComparator.getInstance());
            for (int i = 0; i < messages.size() - MAX_MESSAGES_SIZE; i++) {
                dao.delete(messages.get(i));
            }
        }
    }

    public void clear() {
        ChatApplication.getInstance().getSession().getMessageEntityDao().deleteAll();
    }

    private Observable<List<TextMessage>> getTestMessages() {
        return Observable.interval(UPDATE_INTERVAL, UPDATE_INTERVAL, TimeUnit.SECONDS)
                .flatMap(generate())
                .doOnNext(save());
    }

    private Observable<List<TextMessage>> getDBMessages() {
        return ChatApplication.getInstance().getSession().getMessageEntityDao().rx().loadAll()
                .flatMap(convert());
    }

    private Action1<List<TextMessage>> save() {
        return new Action1<List<TextMessage>>() {
            public void call(List<TextMessage> textMessages) {
                clearOld();
                UserEntityDao userDao = ChatApplication.getInstance().getSession().getUserEntityDao();
                MessageEntityDao messageDao = ChatApplication.getInstance().getSession().getMessageEntityDao();
                for (TextMessage message : textMessages) {
                    List<UserEntity> users = userDao.queryBuilder().where(UserEntityDao.Properties.Uuid.eq(message.getUserId())).list();
                    MessageEntity entity = new MessageEntity(null, message.getText(), users.get(0).getId(), message.getDate());
                    messageDao.insert(entity);
                }
            }
        };
    }

    private Func1<Long, Observable<List<TextMessage>>> generate() {
        return new Func1<Long, Observable<List<TextMessage>>>() {
            public Observable<List<TextMessage>> call(Long aLong) {
                List<TextMessage> messages = new ArrayList<>(1);
                TextMessage message = new TextMessage();
                message.setText(getRandomMessage());
                message.setDate(System.currentTimeMillis());
                message.setSource(MessageSource.EXTERNAL_USER);
                message.setAvatarUrl(TestUserLoader.TEST_AVATAR);
                message.setDisplayName("Test");
                message.setUserId("test");
                messages.add(message);
                return Observable.just(messages);
            }
        };
    }

    private Func1<List<MessageEntity>, Observable<List<TextMessage>>> convert() {
        return new Func1<List<MessageEntity>, Observable<List<TextMessage>>>() {
            public Observable<List<TextMessage>> call(List<MessageEntity> entities) {
                List<TextMessage> messages = new ArrayList<>(entities.size());
                for (MessageEntity entity : entities) {
                    TextMessage message = new TextMessage();
                    message.setText(entity.getMessage());
                    message.setDate(entity.getDate());
                    message.setAvatarUrl(entity.getUser().getAvatar());
                    message.setDisplayName(entity.getUser().getName());
                    message.setSource(entity.getUser().getSource());
                    message.setUserId(entity.getUser().getUuid());
                    messages.add(message);
                }
                return Observable.just(messages);
            }
        };
    }
}