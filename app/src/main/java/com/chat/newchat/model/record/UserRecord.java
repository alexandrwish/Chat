package com.chat.newchat.model.record;

public class UserRecord {

    private String name;
    private String uuid;
    private String avatar;
    private String source;

    public UserRecord() {
    }

    public UserRecord(String name, String uuid, String avatar, String source) {
        this.name = name;
        this.uuid = uuid;
        this.avatar = avatar;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String toString() {
        return "UserRecord{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}