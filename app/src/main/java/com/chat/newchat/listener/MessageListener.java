package com.chat.newchat.listener;

import java.util.List;

import it.slyce.messaging.message.Message;
import it.slyce.messaging.message.TextMessage;

public interface MessageListener {

    void show(List<Message> messages);

    void show(TextMessage message);

    void clear();
}