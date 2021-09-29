package com.avocado.tatevik.retail.service.order.validator;

import com.avocado.tatevik.retail.common.exception.order.OrderNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class OrderTotalPriceValidator {

    public void validateTotalPrice(BigDecimal totalPrice, BigDecimal originalPrice, BigDecimal discount, BigDecimal paidFromBonus) {
        if (totalPrice.compareTo(calculateTotalPrice(originalPrice, discount, paidFromBonus)) != 0) {
            throw new OrderNotValidException("The provided orders total price is wrong", ExceptionCode.UUTI_45);
        }
    }

    private BigDecimal calculateTotalPrice(BigDecimal originalPrice,BigDecimal discount, BigDecimal paidFromBonus) {
        return originalPrice.subtract(calculateDiscount(originalPrice, discount)).subtract(paidFromBonus);
    }

    private BigDecimal calculateDiscount(BigDecimal originalPrice, BigDecimal discount) {
        return originalPrice.multiply(discount).divide(BigDecimal.valueOf(100),2, RoundingMode.CEILING);
    }
}
