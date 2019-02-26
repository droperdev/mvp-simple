package com.droperdev.overall.interfaces.group;


public interface GroupInteractor {

    void save(String groupName, GroupListener groupListener);

    void getGroups(GroupListener groupListener);

    void delete(GroupListener groupListener);

    void deleteForId(long id, GroupListener groupListener);
}
