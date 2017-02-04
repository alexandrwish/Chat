package com.chat.newchat.interactor;

import com.chat.newchat.model.loader.UserLoader;
import com.chat.newchat.model.record.UserRecord;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class UserInteractor extends AbstractInteractor<List<UserRecord>> {

    private final UserLoader mLoader;

    @Inject
    public UserInteractor(UserLoader loader) {
        mLoader = loader;
    }

    protected Observable<List<UserRecord>> build() {
        return mLoader.loadAll();
    }
}