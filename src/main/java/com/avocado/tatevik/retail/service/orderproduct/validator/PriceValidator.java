package com.avocado.tatevik.retail.service.orderproduct.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceValidator {

    @Autowired
    OriginalPriceValidator originalPriceValidator;

    @Autowired
    TotalPriceValidator totalPriceValidator;

    public void isValid(BigDecimal price, BigDecimal totalPrice, BigDecimal originalPrice, BigDecimal discount, BigDecimal amount) {
        originalPriceValidator.isValid(price, originalPrice);
        totalPriceValidator.validateTotalPrice(totalPrice, originalPrice, discount, amount);
    }
}
