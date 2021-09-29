package com.avocado.tatevik.retail.service.orderproduct.converter;

import com.avocado.tatevik.retail.repository.orderproduct.entity.OrderProductEntity;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderProductEntityConverter {

    public OrderProductModel convert(OrderProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return new OrderProductModel(
                entity.getId(),
                entity.getProductId(),
                entity.getOrderId(),
                entity.getAmount(),
                entity.getComment(),
                entity.getOriginalPrice(),
                entity.getDiscount(),
                entity.getTotalPrice());
    }

    public List<OrderProductModel> convert(List<OrderProductEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
