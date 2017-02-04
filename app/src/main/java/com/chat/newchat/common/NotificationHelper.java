package com.chat.newchat.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import com.chat.newchat.ChatApplication;
import com.chat.newchat.R;
import com.chat.newchat.view.activity.ChatActivity;

public class NotificationHelper {

    public static final int MAIN_NOTIFICATION_ID = 2;
    private static final int NOTIFICATION_ID = 1;

    public static void showNotification(Context context, String message) {
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(NOTIFICATION_ID, createNotification(context, message, ChatApplication.getInstance().getString(R.string.new_message)));
    }

    public static void hideNotification(Context context) {
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).cancel(NOTIFICATION_ID);
    }

    public static Notification createNotification(Context context, String message, String title) {
        return new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(TaskStackBuilder.create(context)
                        .addParentStack(ChatActivity.class)
                        .addNextIntent(new Intent(context, ChatActivity.class))
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();
    }
}