package com.avocado.tatevik.retail.controller.order.converter;

import com.avocado.tatevik.retail.controller.order.dto.OrderCreationDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderUpdateDto;
import com.avocado.tatevik.retail.controller.orderproduct.converter.OrderProductDtoConverter;
import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.order.model.OrderUpdateModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoConverter {

    @Autowired
    private OrderProductDtoConverter orderProductDtoConverter;

    public OrderModel convert(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        List<OrderProductModel> orderProducts = orderProductDtoConverter.convert(dto.getOrderProducts());
        return new OrderModel(
                dto.getId(),
                dto.getCustomerId(),
                dto.getAddressId(),
                dto.getShopId(),
                dto.getOriginalPrice(),
                dto.getTotalPrice(),
                dto.getPaidFromBonus(),
                dto.getOrderDiscount(),
                dto.getPaymentType(),
                orderProducts);
    }

    public OrderUpdateModel convert(Long id, OrderUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new OrderUpdateModel(
                id,
                dto.getCustomerId(),
                dto.getAddressId(),
                dto.getShopId(),
                dto.getOriginalPrice(),
                dto.getTotalPrice(),
                dto.getPaidFromBonus(),
                dto.getOrderDiscount(),
                dto.getPaymentType());
    }

    public OrderCreationModel convert(OrderCreationDto dto) {
        if (dto == null) {
            return null;
        }
        List<OrderProductCreationModel> orderProducts = orderProductDtoConverter.convertCreationList(dto.getOrderProducts());
        return new OrderCreationModel(
                dto.getCustomerId(),
                dto.getShopId(),
                dto.getAddressId(),
                dto.getOriginalPrice(),
                dto.getTotalPrice(),
                dto.getPaidFromBonus(),
                dto.getOrderDiscount(),
                dto.getPaymentType(),
                orderProducts);
    }
}
