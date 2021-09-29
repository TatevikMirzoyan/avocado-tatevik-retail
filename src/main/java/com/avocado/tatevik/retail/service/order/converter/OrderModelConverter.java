package com.avocado.tatevik.retail.service.order.converter;

import com.avocado.tatevik.retail.controller.order.dto.OrderDto;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductDto;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.order.model.OrderUpdateModel;
import com.avocado.tatevik.retail.service.orderproduct.converter.OrderProductModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderModelConverter {

    @Autowired
    private OrderProductModelConverter orderProductModelConverter;

    public OrderDto convert(OrderModel model) {
        if (model == null) {
            return null;
        }
        List<OrderProductDto> orderProducts = orderProductModelConverter.convertModels(model.getOrderProducts());
        return new OrderDto(
                model.getId(),
                model.getCustomerId(),
                model.getShopId(),
                model.getAddressId(),
                model.getOriginalPrice(),
                model.getTotalPrice(),
                model.getPaidFromBonus(),
                model.getOrderDiscount(),
                model.getPaymentType(),
                orderProducts);
    }

    public OrderEntity convert(OrderUpdateModel model, OrderEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setOrderDiscount(model.getOrderDiscount());
        entity.setOriginalPrice(model.getOriginalPrice());
        entity.setPaidFromBonus(model.getPaidFromBonus());
        entity.setTotalPrice(model.getTotalPrice());
        entity.setPaymentType(model.getPaymentType());
        return entity;
    }

    public OrderEntity convert(OrderCreationModel model) {
        if (model == null) {
            return null;
        }
        OrderEntity entity = new OrderEntity();
        entity.setOrderDiscount(model.getOrderDiscount());
        entity.setOriginalPrice(model.getOriginalPrice());
        entity.setPaidFromBonus(model.getPaidFromBonus());
        entity.setTotalPrice(model.getTotalPrice());
        entity.setPaymentType(model.getPaymentType());
        return entity;
    }
}
