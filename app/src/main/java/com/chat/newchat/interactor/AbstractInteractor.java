package com.chat.newchat.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class AbstractInteractor<R> {

    private final CompositeSubscription mSubscription = new CompositeSubscription();

    protected abstract Observable<R> build();

    public void subscribe(Subscriber<R> subscriber) {
        mSubscription.add(build()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
        );
    }

    public void unSubscribe() {
        mSubscription.clear();
    }
}