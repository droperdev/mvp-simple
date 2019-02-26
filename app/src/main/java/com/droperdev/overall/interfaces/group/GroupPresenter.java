package com.droperdev.overall.interfaces.group;

public interface GroupPresenter {
    void save(String groupName);
    void getGroups();
    void delete();

    void deleteForId(long id);
}
