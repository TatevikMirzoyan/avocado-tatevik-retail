package com.avocado.tatevik.retail.repository.order;

import com.avocado.tatevik.retail.controller.order.dto.OrderSearchQueryParams;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;

import java.util.List;
import java.util.Set;

public interface OrdersSearchRepository {

    Set<OrderEntity> search(OrderSearchQueryParams request);
}
