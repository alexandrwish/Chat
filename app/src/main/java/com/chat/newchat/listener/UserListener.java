package com.chat.newchat.listener;

import com.chat.newchat.model.record.UserRecord;

import java.util.Collection;

public interface UserListener {

    void onLoad(Collection<UserRecord> users);

    void onConnect(UserRecord user);

    void onDisconnect(UserRecord user);
}