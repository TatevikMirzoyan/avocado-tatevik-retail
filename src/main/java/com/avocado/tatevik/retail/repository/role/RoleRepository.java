package com.avocado.tatevik.retail.repository.role;

import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Modifying
    @Query(value = "UPDATE RoleEntity SET deleted = 'true' WHERE id = :id")
    void updateDeleted(@Param("id") Long id);
}
