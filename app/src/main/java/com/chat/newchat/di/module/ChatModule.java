package com.chat.newchat.di.module;

import com.chat.newchat.model.loader.MessageLoader;
import com.chat.newchat.model.loader.TestMessageLoader;
import com.chat.newchat.model.loader.TestUserLoader;
import com.chat.newchat.model.loader.UserLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatModule {

    @Singleton
    @Provides
    public UserLoader provideUserLoader() {
        return new TestUserLoader();
    }

    @Singleton
    @Provides
    public MessageLoader provideMessageLoader() {
        return new TestMessageLoader();
    }
}