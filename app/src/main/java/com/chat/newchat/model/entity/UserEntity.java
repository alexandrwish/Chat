package com.chat.newchat.model.entity;

import com.chat.newchat.model.db.SourceConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import it.slyce.messaging.message.MessageSource;

@Entity(nameInDb = "user")
public class UserEntity {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Index(unique = true)
    private String uuid;
    private String avatar;
    @Convert(converter = SourceConverter.class, columnType = String.class)
    private MessageSource source;

    @Generated(hash = 1003474905)
    public UserEntity(Long id, String name, String uuid, String avatar,
                      MessageSource source) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.avatar = avatar;
        this.source = source;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public MessageSource getSource() {
        return this.source;
    }

    public void setSource(MessageSource source) {
        this.source = source;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}