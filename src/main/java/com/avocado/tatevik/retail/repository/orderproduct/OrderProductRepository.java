package com.avocado.tatevik.retail.repository.orderproduct;

import com.avocado.tatevik.retail.repository.orderproduct.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
}
