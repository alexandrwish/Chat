package com.chat.newchat.listener;

import android.net.Uri;

import com.chat.newchat.presenter.MessagePresenter;

import it.slyce.messaging.listeners.UserSendsMessageListener;

public class UserMessageListener implements UserSendsMessageListener {

    private final MessagePresenter mPresenter;

    public UserMessageListener(MessagePresenter presenter) {
        mPresenter = presenter;
    }

    public void onUserSendsTextMessage(String text) {
        mPresenter.onNewMessage(text);
    }

    public void onUserSendsMediaMessage(Uri imageUri) {
        // TODO: 2/4/17 maybe later
    }
}