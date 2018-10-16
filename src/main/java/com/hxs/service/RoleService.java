package com.hxs.service;

import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.graphql.types.PermissionInput;
import com.hxs.graphql.types.RoleInput;
import com.hxs.graphql.types.UserInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author HSteidel
 */
public interface RoleService {
    Role createRole(String roleName);

    Role getRoleOrThrowNotFound(Long roleId);

    void deleteRole(Long id);

    Role addPermission(Long roleId, Long permId);

    Role addUserToRole(Long roleId, Long userId);

    Iterable<Role> findAll();
}
