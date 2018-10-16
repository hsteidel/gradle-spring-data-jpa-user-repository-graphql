package com.hxs.service.rdbms;

import com.hxs.data.models.Permission;
import com.hxs.data.repositories.PermissionsRepository;
import com.hxs.graphql.types.PermissionInput;
import com.hxs.service.PermissionsService;
import com.hxs.service.exceptions.EntityNotFoundException;
import com.hxs.service.exceptions.ResourceConflictException;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hsteidel
 */
@Service
@Transactional
@Profile("rdbms")
public class RelationalPermissionsService implements PermissionsService {

    private final PermissionsRepository permissionsRepository;

    @Autowired
    public RelationalPermissionsService(PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }

    @Override
    public Permission getPermOrThrowNotFound(Long id){
        return Option.ofOptional(permissionsRepository.findById(id))
                .getOrElseThrow(() -> new EntityNotFoundException(Permission.class, id));
    }


    @Override
    public Permission createPermission(Permission permissionRequest) {

        if(permissionsRepository.findByName(permissionRequest.getName()) != null){
            throw new ResourceConflictException("Permission with that name already exists.");
        }

        Permission permission = new Permission();
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        return permissionsRepository.save(permission);
    }

    @Override
    public void delete(Long id) {
        permissionsRepository.deleteById(id);
    }

    @Override
    public Iterable<Permission> findAll(){
        return permissionsRepository.findAll();
    }
}
