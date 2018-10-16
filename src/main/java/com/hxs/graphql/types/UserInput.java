package com.hxs.graphql.types;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @author hsteidel
 */
public class UserInput {

    private Long id;

    private String username;

    private String password;

    private Set<RoleInput> roles;

    private Set<PermissionInput> permissions;

    private Set<GroupInput> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleInput> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleInput> roles) {
        this.roles = roles;
    }

    public Set<PermissionInput> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionInput> permissions) {
        this.permissions = permissions;
    }

    public Set<GroupInput> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupInput> groups) {
        this.groups = groups;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
