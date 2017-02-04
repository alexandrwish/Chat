package com.chat.newchat.presenter;

import android.util.Log;

import com.chat.newchat.ChatApplication;
import com.chat.newchat.interactor.MessageInteractor;
import com.chat.newchat.listener.MessageListener;

import java.util.List;

import javax.inject.Inject;

import it.slyce.messaging.message.TextMessage;
import rx.Subscriber;

public class MessagePresenter extends AbstractPresenter<MessageListener> {

    private final MessageInteractor mInteractor;

    @Inject
    public MessagePresenter(MessageInteractor interactor) {
        mInteractor = interactor;
    }

    public void onStart() {
        ChatApplication.getInstance().unSubscribe();
        mInteractor.subscribe(new Subscriber<List<TextMessage>>() {
            public void onCompleted() {

            }

            public void onError(Throwable e) {
                Log.e(MessagePresenter.class.getName(), e.getMessage(), e);
            }

            public void onNext(List<TextMessage> messages) {
                for (TextMessage message : messages) {
                    mListener.show(message);
                }
            }
        });
    }

    public void onStop() {
        mInteractor.unSubscribe();
        ChatApplication.getInstance().subscribe(mInteractor);
    }

    public void clearMessages() {
        mListener.clear();
        mInteractor.clearMessages();
    }

    public void onNewMessage(String text) {
        mInteractor.saveMessage(text);
    }
}