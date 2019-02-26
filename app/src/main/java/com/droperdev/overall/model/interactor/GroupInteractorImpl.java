package com.droperdev.overall.model.interactor;

import android.content.Context;
import android.os.Handler;

import com.droperdev.overall.interfaces.group.GroupInteractor;
import com.droperdev.overall.interfaces.group.GroupListener;
import com.droperdev.overall.model.entities.Group;

import io.realm.Realm;

import io.realm.RealmResults;

public class GroupInteractorImpl implements GroupInteractor {
    private final Context mContext;
    private final Realm mRealm;

    public GroupInteractorImpl(Context context){
        this.mContext = context;
        this.mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void save(String groupName, GroupListener groupListener)
    {
        new Handler().postDelayed(()-> {
            mRealm.beginTransaction();
            Number currentIdNum = mRealm.where(Group.class).max("id");
            int nextId = currentIdNum == null? 1: currentIdNum.intValue() + 1;
            Group group = new Group();
            group.setId(nextId);
            group.setName(groupName);
            mRealm.copyToRealmOrUpdate(group);
            mRealm.commitTransaction();
            groupListener.saveSuccessful();
        },100);
    }

    @Override
    public void getGroups(GroupListener groupListener) {
        RealmResults<Group> groups = mRealm.where(Group.class).findAll();
        groupListener.setGroups(groups);

    }

    @Override
    public void delete(GroupListener groupListener) {
        new Handler().postDelayed(()-> {
            mRealm.beginTransaction();
            mRealm.delete(Group.class);
            mRealm.commitTransaction();
            groupListener.deleteSuccessful();
        }, 100);

    }

    @Override
    public void deleteForId(long id, GroupListener groupListener) {
            mRealm.beginTransaction();
            Group group  = mRealm.where(Group.class).equalTo("id", id).findFirst();
            if (group != null){
                group.deleteFromRealm();
            }
            mRealm.commitTransaction();
            groupListener.deleteSuccessful();
    }
}
