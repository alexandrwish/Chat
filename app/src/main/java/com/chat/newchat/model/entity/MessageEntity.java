package com.chat.newchat.model.entity;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(nameInDb = "message")
public class MessageEntity {

    @Id(autoincrement = true)
    private Long id;
    private String message;
    private Long userId;
    @ToOne(joinProperty = "userId")
    private UserEntity user;
    private long date;
    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 499759967)
    private transient MessageEntityDao myDao;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 311989702)
    public MessageEntity(Long id, String message, Long userId, long date) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.date = date;
    }

    @Generated(hash = 1797882234)
    public MessageEntity() {
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 2105083445)
    public UserEntity getUser() {
        Long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserEntityDao targetDao = daoSession.getUserEntityDao();
            UserEntity userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 720140878)
    public void setUser(UserEntity user) {
        synchronized (this) {
            this.user = user;
            userId = user == null ? null : user.getId();
            user__resolvedKey = userId;
        }
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 83651317)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMessageEntityDao() : null;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}