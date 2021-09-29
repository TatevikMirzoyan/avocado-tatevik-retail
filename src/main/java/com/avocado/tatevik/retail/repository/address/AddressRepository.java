package com.avocado.tatevik.retail.repository.address;

import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Modifying
    @Query(value = "UPDATE AddressEntity SET deleted = 'true' WHERE id = :id")
    void updateDeleted(@Param("id") Long id);
}
