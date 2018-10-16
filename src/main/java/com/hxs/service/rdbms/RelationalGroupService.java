package com.hxs.service.rdbms;

import com.hxs.data.models.Group;
import com.hxs.data.models.User;
import com.hxs.data.repositories.GroupRepository;
import com.hxs.data.repositories.UserRepository;
import com.hxs.graphql.types.GroupInput;
import com.hxs.graphql.types.UserInput;
import com.hxs.service.GroupService;
import com.hxs.service.exceptions.EntityNotFoundException;
import com.hxs.service.exceptions.ResourceConflictException;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hsteidel
 */
@Service
@Transactional
@Profile("rdbms")
public class RelationalGroupService implements GroupService {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    @Autowired
    public RelationalGroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Group getByID(Long id) {
        return Option.ofOptional(groupRepository.findById(id)).get();
    }


    @Override
    public Group getGroupOrThrowNotFound(Long id) {
        Group group = getByID(id);

        if(group == null){
            throw new EntityNotFoundException(Group.class, id);
        }

        return group;
    }

    @Override
    public Group createGroup(Group groupRequest) {

        if(groupRepository.findByName(groupRequest.getName()) != null){
            throw new ResourceConflictException("Group already exists with that name");
        }

        Group group = new Group();
        group.setName(groupRequest.getName());
        group.setDescription(groupRequest.getDescription());
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Group addUsersToGroup(Long id, List<UserInput> users) {
        Group group = getGroupOrThrowNotFound(id);
        group.setUsers(getUserEntities(users));
        return groupRepository.save(group);
    }


    @Override
    public Set<User> getUserEntities(List<UserInput> userRequestList) {
        return userRepository.findByIdIn(userRequestList.stream()
                .map(UserInput::getId)
                .collect(Collectors.toList()));
    }

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }
}
