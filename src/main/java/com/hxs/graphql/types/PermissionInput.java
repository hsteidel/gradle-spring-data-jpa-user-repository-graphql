package com.hxs.graphql.types;

import java.util.Objects;

public class PermissionInput {

    private Long id;

    private String name;

    private String description;

    public PermissionInput() {
    }

    public PermissionInput(String name, String description) {
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

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionInput that = (PermissionInput) o;
        return Objects.equals(id, that.id);
    }

    
    public int hashCode() {
        return Objects.hash(id);
    }
}
