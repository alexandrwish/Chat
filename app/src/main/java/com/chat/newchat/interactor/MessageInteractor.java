package com.chat.newchat.interactor;

import com.chat.newchat.model.loader.MessageLoader;

import java.util.List;

import javax.inject.Inject;

import it.slyce.messaging.message.TextMessage;
import rx.Observable;

public class MessageInteractor extends AbstractInteractor<List<TextMessage>> {

    private final MessageLoader mLoader;

    @Inject
    public MessageInteractor(MessageLoader loader) {
        mLoader = loader;
    }

    protected Observable<List<TextMessage>> build() {
        return mLoader.loadAll();
    }

    public void saveMessage(String text) {
        mLoader.save(text);
    }

    public void clearMessages() {
        mLoader.clear();
    }
}