package com.avocado.tatevik.retail.repository.shop;

import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {

    @Query(value = "SELECT s FROM ShopEntity s WHERE s.name = :name")
    Optional<ShopEntity> findByName(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE ShopEntity SET deleted = 'true' WHERE id = :id")
    void updateDeleted(@Param("id") Long id);
}
