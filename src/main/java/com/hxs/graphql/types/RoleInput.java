package com.hxs.graphql.types;

import java.util.Objects;
import java.util.Set;


public class RoleInput {

    private Long id;

    private String name;

    private Set<UserInput> users;

    private Set<PermissionInput> permissions;

    public RoleInput() {

    }

    public RoleInput(String name) {
        setName(name);

    }

    public RoleInput(String name, Set<UserInput> users) {
        setName(name);
        setUsers(users);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserInput> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserInput> users) {
        this.users = users;
    }

    public Set<PermissionInput> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionInput> permissions) {
        this.permissions = permissions;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleInput role = (RoleInput) o;
        return Objects.equals(id, role.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}
