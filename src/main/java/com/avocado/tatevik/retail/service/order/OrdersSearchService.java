package com.avocado.tatevik.retail.service.order;

import com.avocado.tatevik.retail.controller.order.dto.OrderSearchQueryParams;
import com.avocado.tatevik.retail.repository.order.OrdersSearchRepository;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import com.avocado.tatevik.retail.service.order.converter.OrderEntityConverter;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrdersSearchService {

    @Autowired
    private OrdersSearchRepository ordersSearchRepository;

    @Autowired
    private OrderEntityConverter orderEntityConverter;

    public Set<OrderModel> search(OrderSearchQueryParams request) {
        Set<OrderEntity> entities = ordersSearchRepository.search(request);

        return entities
                .stream()
                .map(entity -> orderEntityConverter.convert(entity))
                .collect(Collectors.toSet());
    }
}
