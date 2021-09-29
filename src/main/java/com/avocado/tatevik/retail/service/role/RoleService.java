package com.avocado.tatevik.retail.service.role;

import com.avocado.tatevik.retail.repository.role.RoleRepository;
import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity get(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role entity with id " + id + " does not exist."));
    }

    public RoleEntity create(RoleEntity role) {
        return roleRepository.save(role);
    }

    public RoleEntity update(RoleEntity role) {
        RoleEntity entity = roleRepository.findById(role.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role entity with id " + role.getId() + " does not exist."));
        //update
        entity.setRole(role.getRole());
        entity.setPermissions(role.getPermissions());
        entity = roleRepository.save(entity);
        return entity;
    }

    @Transactional
    public Boolean delete(Long id) {
        roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role entity with id " + id + " does not exist."));
        roleRepository.updateDeleted(id);
        return true;
    }
}
