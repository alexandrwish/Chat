package com.chat.newchat.di.component;

import com.chat.newchat.di.module.ChatModule;
import com.chat.newchat.view.activity.ChatActivity;
import com.chat.newchat.view.fragment.UsersFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ChatModule.class})
public interface ChatComponent {

    void inject(ChatActivity activity);

    void inject(UsersFragment fragment);
}