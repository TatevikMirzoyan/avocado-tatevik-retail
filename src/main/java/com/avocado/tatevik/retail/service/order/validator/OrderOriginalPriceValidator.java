package com.avocado.tatevik.retail.service.order.validator;

import com.avocado.tatevik.retail.common.exception.order.OrderNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class OrderOriginalPriceValidator {

    public void isValid(BigDecimal originalPrice, List<OrderProductCreationModel> orderProducts) {
        if (orderProducts == null) {
            throw new OrderNotValidException("Order products can not be null", ExceptionCode.UUTI_45);
        } else if (
                new BigDecimal(
                        orderProducts
                                .stream()
                                .filter(Objects::nonNull)
                                .mapToInt(opcm -> opcm.getTotalPrice().intValue())
                                .sum())
                        .compareTo(originalPrice) != 0)
            throw new OrderNotValidException("The provided order original price is wrong", ExceptionCode.UUTI_45);
    }
}