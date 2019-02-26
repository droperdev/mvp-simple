package com.droperdev.overall.presenter;

import android.content.Context;

import com.droperdev.overall.interfaces.group.GroupInteractor;
import com.droperdev.overall.interfaces.group.GroupListener;
import com.droperdev.overall.interfaces.group.GroupPresenter;
import com.droperdev.overall.interfaces.group.GroupView;
import com.droperdev.overall.model.entities.Group;
import com.droperdev.overall.model.interactor.GroupInteractorImpl;

import io.realm.RealmResults;

public class GroupPresenterImpl implements GroupPresenter, GroupListener {
    private final GroupView mGroupView;
    private GroupInteractor mGroupInteractor;
    public GroupPresenterImpl(GroupView groupView, Context context){
        this.mGroupView = groupView;
        this.mGroupInteractor = new GroupInteractorImpl(context);
    }

    @Override
    public void save(String groupName) {
        if (mGroupView != null) {
            mGroupView.showProgress("Guardando...");
            mGroupInteractor.save(groupName, this);
        }
    }

    @Override
    public void getGroups() {
        if (mGroupView != null) {
            mGroupInteractor.getGroups(this);
        }
    }

    @Override
    public void delete() {
        if (mGroupView!= null){
            mGroupView.showProgress("Eliminando grupos...");
            mGroupInteractor.delete(this);
        }
    }

    @Override
    public void deleteForId(long id) {
        if (mGroupView != null){
            mGroupView.showProgress(String.format("Eliminando grupo con id %s", id));
            mGroupInteractor.deleteForId(id, this);
        }
    }

    @Override
    public void saveSuccessful()
    {
        mGroupView.hideProgress();
        mGroupView.saveSuccessful();
    }

    @Override
    public void saveFailure(Throwable error) {
        mGroupView.hideProgress();
        mGroupView.saveFailure(error);
    }

    @Override
    public void setGroups(RealmResults<Group> groups) {
        mGroupView.showGroups(groups);
    }

    @Override
    public void deleteSuccessful() {
        mGroupView.hideProgress();
        mGroupView.deleteSuccessful();
    }
}
