package com.avocado.tatevik.retail.controller.orderproduct.converter;

import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductCreationDto;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductDto;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductUpdateDto;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductUpdateModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderProductDtoConverter {

    public OrderProductModel convert(OrderProductDto dto) {
        if (dto == null) {
            return null;
        }
        return new OrderProductModel(
                dto.getId(),
                dto.getProductId(),
                dto.getOrderId(),
                dto.getAmount(),
                dto.getComment(),
                dto.getOriginalPrice(),
                dto.getDiscount(),
                dto.getTotalPrice());
    }

    public List<OrderProductModel> convert(List<OrderProductDto> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public OrderProductUpdateModel convert(Long id, OrderProductUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new OrderProductUpdateModel(
                id,
                dto.getProductId(),
                dto.getOrderId(),
                dto.getAmount(),
                dto.getComment(),
                dto.getOriginalPrice(),
                dto.getDiscount(),
                dto.getTotalPrice());
    }

    public OrderProductCreationModel convert(OrderProductCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new OrderProductCreationModel(
                dto.getProductId(),
                dto.getOrderId(),
                dto.getAmount(),
                dto.getComment(),
                dto.getOriginalPrice(),
                dto.getDiscount(),
                dto.getTotalPrice());
    }

    public List<OrderProductCreationModel> convertCreationList(List<OrderProductCreationDto> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
