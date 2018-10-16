package com.hxs.service;

import com.hxs.data.models.Permission;
import com.hxs.graphql.types.PermissionInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author HSteidel
 */
public interface PermissionsService {

    Permission getPermOrThrowNotFound(Long id);

    Permission createPermission(Permission permissionRequest);

    void delete(Long id);

    Iterable<Permission> findAll();
}
