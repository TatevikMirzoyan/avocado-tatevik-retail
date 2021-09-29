package com.avocado.tatevik.retail.repository.permission;

import com.avocado.tatevik.retail.repository.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Modifying
    @Query(value = "UPDATE Permission SET deleted = 'true' WHERE id = :id")
    void updateDeleted(@Param("id") Long id);
}
