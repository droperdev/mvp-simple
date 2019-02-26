package com.droperdev.overall.interfaces.group;

import com.droperdev.overall.model.entities.Group;

import io.realm.RealmResults;


public interface GroupListener {

    void saveSuccessful();

    void saveFailure(Throwable error);

    void setGroups(RealmResults<Group> groups);

    void deleteSuccessful();
}
