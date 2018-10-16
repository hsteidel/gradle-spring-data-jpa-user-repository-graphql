package com.hxs.service;

import com.hxs.data.models.Group;
import com.hxs.data.models.User;
import com.hxs.graphql.types.GroupInput;
import com.hxs.graphql.types.UserInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author HSteidel
 */
public interface GroupService {

    Group getByID(Long id);

    Group getGroupOrThrowNotFound(Long id);

    Group createGroup(Group groupRequest);

    void deleteGroup(Long id);

    Group addUsersToGroup(Long id, List<UserInput> users);

    Set<User> getUserEntities(List<UserInput> userRequestList);

    Iterable<Group> findAll();
}
