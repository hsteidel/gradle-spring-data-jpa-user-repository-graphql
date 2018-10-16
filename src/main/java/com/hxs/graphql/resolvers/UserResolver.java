package com.hxs.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hxs.data.models.Group;
import com.hxs.data.models.Permission;
import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hsteidel
 */
@Component
public class UserResolver implements GraphQLResolver<User> {

    private final UserService userService;

    @Autowired
    public UserResolver(UserService userService) {
        this.userService = userService;
    }


    public Iterable<Permission> permissions(User user){
        return userService.getOrThrowUserNotFound(user.getId()).getPermissions();
    }

    public Iterable<Group> groups(User user){
        return userService.getOrThrowUserNotFound(user.getId()).getGroups();
    }

    public Iterable<Role> roles(User user){
        return userService.getOrThrowUserNotFound(user.getId()).getRoles();
    }

    public List<GrantedAuthority> authorities(User user){
        return (List<GrantedAuthority>) userService.getOrThrowUserNotFound(user.getId()).getAuthorities();
    }
}
