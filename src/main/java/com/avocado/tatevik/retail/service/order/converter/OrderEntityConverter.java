package com.avocado.tatevik.retail.service.order.converter;

import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import com.avocado.tatevik.retail.service.orderproduct.converter.OrderProductEntityConverter;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEntityConverter {

    @Autowired
    private OrderProductEntityConverter orderProductEntityConverter;

    public OrderModel convert(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        List<OrderProductModel> orderProducts = orderProductEntityConverter.convert(entity.getOrderProducts());
        return new OrderModel(
                entity.getId(),
                getCustomerId(entity),
                getShopId(entity),
                getAddressId(entity),
                entity.getOriginalPrice(),
                entity.getTotalPrice(),
                entity.getPaidFromBonus(),
                entity.getOrderDiscount(),
                entity.getPaymentType(),
                orderProducts);
    }

    private Long getAddressId(OrderEntity entity) {
        return entity.getAddress() == null ? null : entity.getAddress().getId();
    }

    private Long getCustomerId(OrderEntity entity) {
        return entity.getCustomer() == null ? null : entity.getCustomer().getId();
    }

    private Long getShopId(OrderEntity entity) {
        return entity.getShop() == null ? null : entity.getShop().getId();
    }
}
