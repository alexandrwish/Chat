package com.chat.newchat.presenter;

import android.util.Log;

import com.chat.newchat.interactor.UserInteractor;
import com.chat.newchat.listener.UserListener;
import com.chat.newchat.model.record.UserRecord;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class UserPresenter extends AbstractPresenter<UserListener> {

    private final UserInteractor mInteractor;

    @Inject
    public UserPresenter(UserInteractor interactor) {
        mInteractor = interactor;
    }

    public void onStart() {
        mInteractor.subscribe(new Subscriber<List<UserRecord>>() {
            public void onCompleted() {

            }

            public void onError(Throwable e) {
                Log.e(UserPresenter.class.getName(), e.getMessage(), e);
            }

            public void onNext(List<UserRecord> users) {
                mListener.onLoad(users);
            }
        });
    }

    public void onStop() {
        mInteractor.unSubscribe();
    }
}