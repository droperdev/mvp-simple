package com.droperdev.overall.interfaces.group;

import com.droperdev.overall.model.entities.Group;

import io.realm.RealmResults;


public interface GroupView {
    void showProgress(String message);
    void hideProgress();

    void saveSuccessful();
    void saveFailure(Throwable error);

    void showGroups(RealmResults<Group> groups);

    void deleteSuccessful();
}
