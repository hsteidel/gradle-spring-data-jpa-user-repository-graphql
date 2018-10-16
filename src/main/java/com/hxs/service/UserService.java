package com.hxs.service;

import com.hxs.data.models.Permission;
import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.graphql.types.PasswordChangeInput;
import com.hxs.graphql.types.PermissionInput;
import com.hxs.graphql.types.RoleInput;
import com.hxs.graphql.types.UserInput;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @author HSteidel
 */
public interface UserService {
    User createUser(User userRequest);

    Option<User> maybeGetUserById(Long id);

    Option<User> maybeGetUserByName(String username);

    User getOrThrowUserNotFound(Long id);

    User getOrThrowUserNotFound(String username);

    void changePassword(PasswordChangeInput passwordChangeRequest);

    void deleteUser(Long id);

    User authenticateUser(String username, String password);

    Set<Permission> getPermissions(List<PermissionInput> permissionRequestList);

    Set<Role> getRoles(List<RoleInput> roleRequestList);

    User updateUser(Long id, UserInput userRequest);

    Iterable<User> findAll();
}
