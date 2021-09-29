package com.avocado.tatevik.retail.service.orderproduct.converter;

import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductDto;
import com.avocado.tatevik.retail.repository.orderproduct.entity.OrderProductEntity;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductUpdateModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderProductModelConverter {

    public OrderProductDto convert(OrderProductModel model) {
        if (model == null) {
            return null;
        }
        return new OrderProductDto(
                model.getId(),
                model.getProductId(),
                model.getOrderId(),
                model.getAmount(),
                model.getComment(),
                model.getOriginalPrice(),
                model.getDiscount(),
                model.getTotalPrice());
    }

    public List<OrderProductDto> convertModels(List<OrderProductModel> models) {
        if (models == null) {
            return new ArrayList<>();
        }
        return models
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public OrderProductEntity convert(OrderProductUpdateModel model, OrderProductEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setOrderId(model.getOrderId());
        entity.setProductId(model.getProductId());
        entity.setAmount(model.getAmount());
        entity.setComment(model.getComment());
        entity.setDiscount(model.getDiscount());
        entity.setOriginalPrice(model.getOriginalPrice());
        entity.setTotalPrice(model.getTotalPrice());
        return entity;
    }

    public OrderProductEntity convert(OrderProductCreationModel model) {
        if (model == null) {
            return null;
        }
        OrderProductEntity entity = new OrderProductEntity();
        entity.setOrderId(model.getOrderId());
        entity.setProductId(model.getProductId());
        entity.setAmount(model.getAmount());
        entity.setComment(model.getComment());
        entity.setDiscount(model.getDiscount());
        entity.setOriginalPrice(model.getOriginalPrice());
        entity.setTotalPrice(model.getTotalPrice());
        return entity;
    }

    public List<OrderProductEntity> convert(List<OrderProductCreationModel> models) {
        if (models == null) {
            return new ArrayList<>();
        }
        return models
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
