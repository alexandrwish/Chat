package com.chat.newchat.model.db;

import org.greenrobot.greendao.converter.PropertyConverter;

import it.slyce.messaging.message.MessageSource;

public class SourceConverter implements PropertyConverter<MessageSource, String> {

    public MessageSource convertToEntityProperty(String databaseValue) {
        return MessageSource.valueOf(databaseValue);
    }

    public String convertToDatabaseValue(MessageSource entityProperty) {
        return entityProperty.name();
    }
}