package com.chat.newchat.model.loader;

import com.chat.newchat.ChatApplication;
import com.chat.newchat.model.entity.UserEntity;
import com.chat.newchat.model.entity.UserEntityDao;
import com.chat.newchat.model.record.UserRecord;

import java.util.ArrayList;
import java.util.List;

import it.slyce.messaging.message.MessageSource;
import rx.Observable;
import rx.functions.Action1;

public class TestUserLoader implements UserLoader {

    public static final String MY_AVATAR = "http://media.dcentertainment.com/sites/default/files/GalleryComics_1920x1080_20160427_BM_Cv51_56f33d0526d1a5.59154201.jpg";
    public static final String TEST_AVATAR = "https://lh3.googleusercontent.com/-Y86IN-vEObo/AAAAAAAAAAI/AAAAAAAKyAM/6bec6LqLXXA/s0-c-k-no-ns/photo.jpg";

    public Observable<List<UserRecord>> loadAll() {
        UserRecord record = new UserRecord("test", "test", TEST_AVATAR, MessageSource.EXTERNAL_USER.name());
        UserRecord me = new UserRecord("Me", "me", MY_AVATAR, MessageSource.LOCAL_USER.name());
        List<UserRecord> records = new ArrayList<>(2);
        records.add(me);
        records.add(record);
        return Observable.just(records)
                .doOnNext(new Action1<List<UserRecord>>() {
                    public void call(List<UserRecord> records) {
                        UserEntityDao dao = ChatApplication.getInstance().getSession().getUserEntityDao();
                        for (UserRecord record : records) {
                            if (dao.queryBuilder().where(UserEntityDao.Properties.Uuid.eq(record.getUuid())).count() == 0) {
                                dao.insert(new UserEntity(null, record.getName(), record.getUuid(), record.getAvatar(), MessageSource.valueOf(record.getSource())));
                            }
                        }
                    }
                });
    }
}