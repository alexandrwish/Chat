package com.chat.newchat.model.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chat.newchat.R;
import com.chat.newchat.common.NotificationHelper;

public class CoreService extends Service {

    public void onCreate() {
        super.onCreate();
        startForeground(NotificationHelper.MAIN_NOTIFICATION_ID, NotificationHelper.createNotification(this, "", getString(R.string.app_name)));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}