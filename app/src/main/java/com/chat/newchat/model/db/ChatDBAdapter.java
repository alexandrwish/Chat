package com.chat.newchat.model.db;

import com.chat.newchat.ChatApplication;
import com.chat.newchat.model.entity.DaoMaster;
import com.chat.newchat.model.entity.DaoSession;

public class ChatDBAdapter {

    private static final String DATABASE_NAME = "chat_db";
    private static final Integer DATABASE_VERSION = 1;

    private final DaoSession mSession;

    public ChatDBAdapter() {
        mSession = new DaoMaster(new ChatOpenHelper(ChatApplication.getInstance(), DATABASE_NAME, DATABASE_VERSION).getWritableDatabase()).newSession();
    }

    public DaoSession getSession() {
        return mSession;
    }
}