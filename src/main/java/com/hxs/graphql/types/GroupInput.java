package com.hxs.graphql.types;

import java.util.Set;

public class GroupInput  {

    private Long id;

    private String name;

    private String description;

    private Set<UserInput> users;

    public GroupInput() {
    }

    public GroupInput(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<UserInput> getUsers() {
        return users;
    }
    
    public void setUsers(Set<UserInput> users) {
        this.users = users;
    }
}
