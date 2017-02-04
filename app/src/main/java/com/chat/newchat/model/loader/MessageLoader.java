package com.chat.newchat.model.loader;

import java.util.List;

import it.slyce.messaging.message.TextMessage;
import rx.Observable;

public interface MessageLoader {

    Observable<List<TextMessage>> loadAll();

    void save(String text);

    void clear();
}