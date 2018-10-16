package com.hxs.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hxs.data.models.Group;
import com.hxs.data.models.Permission;
import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.graphql.types.PasswordChangeInput;
import com.hxs.graphql.types.UserInput;
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
public class Mutation implements GraphQLMutationResolver {

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionsService permissionsService;

    private final GroupService groupService;

    @Autowired
    public Mutation(UserService userService, RoleService roleService, PermissionsService permissionsService, GroupService groupService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionsService = permissionsService;
        this.groupService = groupService;
    }

    /** USERS **/

    public User createUser(String username, String password){
        return userService.createUser(new User(username, password, ""));
    }


    public User updateUser(UserInput user){
        return userService.updateUser(user.getId(), user);
    }

    public boolean deleteUser(Long id){
        userService.deleteUser(id);
        return true;
    }

    public boolean changeUserPassword(PasswordChangeInput request){
        userService.changePassword(request);
        return true;
    }

    /** ROLES **/

    public Role createRole(String name){
        return roleService.createRole(name);
    }

    public boolean deleteRole(Long id){
        roleService.deleteRole(id);
        return true;
    }


    /** GROUPS **/

    public Group createGroup(String name, String description){
        return groupService.createGroup(new Group(name, description));
    }

    public boolean deleteGroup(Long id){
        groupService.deleteGroup(id);
        return true;
    }

    /** PERMISSIONS **/

    public Permission createPermission(String name, String description){
        return permissionsService.createPermission(new Permission(name, description));
    }

    public boolean deletePermission(Long id){
        permissionsService.delete(id);
        return true;
    }
}
