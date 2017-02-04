package com.chat.newchat;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.chat.newchat.common.NotificationHelper;
import com.chat.newchat.interactor.MessageInteractor;
import com.chat.newchat.model.db.ChatDBAdapter;
import com.chat.newchat.model.entity.DaoSession;
import com.chat.newchat.model.service.CoreService;

import java.util.List;

import it.slyce.messaging.message.TextMessage;
import rx.Subscriber;

public class ChatApplication extends Application {

    private static ChatApplication sInstance;
    private ChatDBAdapter mAdapter;
    private MessageInteractor mInteractor;

    public static ChatApplication getInstance() {
        return sInstance;
    }

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAdapter = new ChatDBAdapter();
        startService(new Intent(this, CoreService.class));
    }

    public DaoSession getSession() {
        return mAdapter.getSession();
    }

    public void subscribe(MessageInteractor interactor) {
        mInteractor = interactor;
        mInteractor.subscribe(new Subscriber<List<TextMessage>>() {
            public void onCompleted() {

            }

            public void onError(Throwable e) {
                Log.e(ChatApplication.class.getName(), e.getMessage(), e);
            }

            public void onNext(List<TextMessage> textMessages) {
                if (textMessages != null && !textMessages.isEmpty()) {
                    NotificationHelper.showNotification(ChatApplication.this, textMessages.get(0).getText());
                }
            }
        });
    }

    public void unSubscribe() {
        if (mInteractor != null) {
            mInteractor.unSubscribe();
            NotificationHelper.hideNotification(ChatApplication.this);
            mInteractor = null;
        }
    }
}