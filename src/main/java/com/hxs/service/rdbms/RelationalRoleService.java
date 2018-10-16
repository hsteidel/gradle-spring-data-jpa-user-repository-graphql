package com.hxs.service.rdbms;


import com.hxs.data.models.Permission;
import com.hxs.data.models.Role;
import com.hxs.data.models.User;
import com.hxs.data.repositories.PermissionsRepository;
import com.hxs.data.repositories.RoleRepository;
import com.hxs.data.repositories.UserRepository;
import com.hxs.graphql.types.PermissionInput;
import com.hxs.graphql.types.RoleInput;
import com.hxs.graphql.types.UserInput;
import com.hxs.service.RoleService;
import com.hxs.service.exceptions.EntityNotFoundException;
import com.hxs.service.exceptions.ResourceConflictException;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author hsteidel
 */
@Service
@Transactional
@Profile("rdbms")
public class RelationalRoleService implements RoleService {

    private final RoleRepository roleRepository;

    private final PermissionsRepository permissionsRepository;

    private final UserRepository userRepository;

    @Autowired
    public RelationalRoleService(RoleRepository roleRepository, PermissionsRepository permissionsRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.permissionsRepository = permissionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Role createRole(String roleName) {
        if (roleRepository.findByName(roleName) != null) {
            throw new ResourceConflictException("Role already exists.");
        }
        Role role = new Role();
        role.setName(roleName);
        role = roleRepository.save(role);
        return role;
    }


    @Override
    public Role getRoleOrThrowNotFound(Long roleId) {
        return Option.ofOptional(roleRepository.findById(roleId))
                .getOrElseThrow(() -> new EntityNotFoundException(Role.class, roleId));
    }

    @Override
    public void deleteRole(Long id) {
        Role roleToUpdate = getRoleOrThrowNotFound(id);
        if (roleToUpdate != null) {
            if (!roleToUpdate.getUsers().isEmpty()) {
                throw new IllegalStateException("Role is being used.");
            }
            roleRepository.deleteById(id);
        }
    }


    @Override
    public Role addPermission(Long roleId, Long permId) {
        Role roleToUpdate = getRoleOrThrowNotFound(roleId);

        Permission permission = Option.ofOptional(permissionsRepository.findById(permId))
                .getOrElseThrow(() -> new EntityNotFoundException(Permission.class, permId));
        roleToUpdate.getPermissions().add(permission);
        roleRepository.save(roleToUpdate);

        return roleToUpdate;
    }

    @Override
    public Role addUserToRole(Long roleId, Long userId) {
        Role foundRole = getRoleOrThrowNotFound(roleId);

        User user = Option.ofOptional(userRepository.findById(userId))
                .getOrElseThrow(() -> new EntityNotFoundException(User.class, userId));

        foundRole.getUsers().add(user);
        foundRole = roleRepository.save(foundRole);

        user.getRoles().add(foundRole);
        userRepository.save(user);

        return foundRole;
    }

    public Iterable<Role> findAll(){
        return roleRepository.findAll();
    }
}
