package com.avocado.tatevik.retail.repository.order;

import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
