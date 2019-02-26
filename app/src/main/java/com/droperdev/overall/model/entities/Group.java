package com.droperdev.overall.model.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Group extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private RealmList<User> users;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<User> getUsers() {
        return users;
    }

    public void setUsers(RealmList<User> users) {
        this.users = users;
    }
}
