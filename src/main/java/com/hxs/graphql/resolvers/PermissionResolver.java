package com.hxs.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;

import com.hxs.data.models.Permission;
import com.hxs.data.models.User;
import com.hxs.data.repositories.PermissionsRepository;
import com.hxs.data.repositories.UserRepository;
import com.hxs.service.PermissionsService;
import com.hxs.service.UserService;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hsteidel
 */
@Component
public class PermissionResolver implements GraphQLResolver<Permission> {

    private final UserService userService;

    private final PermissionsService permissionsService;

    @Autowired
    public PermissionResolver(UserService userService, PermissionsService permissionsService) {
        this.userService = userService;
        this.permissionsService = permissionsService;
    }


    public Permission findPermission(Permission permission){
        return permissionsService.getPermOrThrowNotFound(permission.getId());
    }


    public Iterable<Permission> permissions(User user){
        return userService.getOrThrowUserNotFound(user.getId()).getPermissions();
    }

}
