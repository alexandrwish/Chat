package com.chat.newchat.common;

import com.chat.newchat.model.entity.MessageEntity;

import java.util.Comparator;

public class MessageComparator implements Comparator<MessageEntity> {

    private static MessageComparator sInstance;

    public static MessageComparator getInstance() {
        if (sInstance == null) {
            sInstance = new MessageComparator();
        }
        return sInstance;
    }

    public int compare(MessageEntity entity1, MessageEntity entity2) {
        return (int) (entity1.getDate() - entity2.getDate());
    }
}