package com.avocado.tatevik.retail.service.permission;

import com.avocado.tatevik.retail.repository.permission.PermissionRepository;
import com.avocado.tatevik.retail.repository.permission.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission get(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission entity with id " + id + " does not exist."));
    }

    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission update(Permission permission) {
        Permission entity = permissionRepository.findById(permission.getId())
                .orElseThrow(() -> new EntityNotFoundException("Permission entity with id " + permission.getId() + " does not exist."));
        entity.setType(permission.getType());
        entity.setRole(permission.getRole());
        entity = permissionRepository.save(entity);
        return entity;
    }

    @Transactional
    public Boolean delete(Long id) {
        permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission entity with id " + id + " does not exist."));
        permissionRepository.updateDeleted(id);
        return true;
    }
}
