package com.hxs.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hxs.data.models.Group;
import com.hxs.data.models.Permission;
import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.service.GroupService;
import com.hxs.service.PermissionsService;
import com.hxs.service.RoleService;
import com.hxs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hsteidel
 */
@Component
public class Query implements GraphQLQueryResolver {

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionsService permissionsService;

    private final GroupService groupService;

    @Autowired
    public Query(UserService userService, RoleService roleService, PermissionsService permissionsService, GroupService groupService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionsService = permissionsService;
        this.groupService = groupService;
    }

    public Iterable<User> findAllUsers(){
        return userService.findAll();
    }

    public Iterable<Permission> findAllPermissions(){
        return permissionsService.findAll();
    }

    public Iterable<Group> findAllGroups(){
        return groupService.findAll();
    }

    public Iterable<Role> findAllRoles(){
        return roleService.findAll();
    }

    public User authenticateUsers(String username, String password){
        return userService.authenticateUser(username, password);
    }
}
