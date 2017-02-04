package com.chat.newchat.model.db;

import android.content.Context;

import com.chat.newchat.model.entity.DaoMaster;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

class ChatOpenHelper extends DatabaseOpenHelper {

    ChatOpenHelper(Context context, String name, int version) {
        super(context, name, version);
    }

    public void onCreate(Database db) {
        DaoMaster.createAllTables(db, false);
    }

    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
        onCreate(db);
    }
}