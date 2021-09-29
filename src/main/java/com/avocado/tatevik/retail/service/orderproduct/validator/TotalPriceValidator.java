package com.avocado.tatevik.retail.service.orderproduct.validator;

import com.avocado.tatevik.retail.common.exception.order.OrderNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TotalPriceValidator {

    public void validateTotalPrice(BigDecimal totalPrice, BigDecimal originalPrice, BigDecimal discount, BigDecimal amount) {
        if (totalPrice.compareTo(calculateTotalPrice(originalPrice, amount, discount)) != 0) {
            throw new OrderNotValidException("The provided order product total price is wrong", ExceptionCode.UUTI_45);
        }
    }

    private BigDecimal calculateTotalPrice(BigDecimal originalPrice,BigDecimal amount, BigDecimal discount) {
        return originalPrice.subtract(calculateDiscount(originalPrice, discount)).multiply(amount);
    }

    private BigDecimal calculateDiscount(BigDecimal originalPrice, BigDecimal discount) {
        return originalPrice.multiply(discount).divide(BigDecimal.valueOf(100),2, RoundingMode.CEILING);
    }
}
