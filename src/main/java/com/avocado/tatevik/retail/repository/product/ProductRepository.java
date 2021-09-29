package com.avocado.tatevik.retail.repository.product;

import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.name = :name")
    Optional<ProductEntity> findByName(@Param("name") String name);
}
