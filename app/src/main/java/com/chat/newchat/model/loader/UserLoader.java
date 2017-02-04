package com.chat.newchat.model.loader;

import com.chat.newchat.model.record.UserRecord;

import java.util.List;

import rx.Observable;

public interface UserLoader {

    Observable<List<UserRecord>> loadAll();
}