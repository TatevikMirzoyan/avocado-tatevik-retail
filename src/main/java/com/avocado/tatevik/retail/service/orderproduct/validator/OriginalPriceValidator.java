package com.avocado.tatevik.retail.service.orderproduct.validator;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.exceptions.OrderNotValidException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OriginalPriceValidator {

    public void isValid(BigDecimal price, BigDecimal originalPrice) {
        if (price == null) {
            if (originalPrice != null)
                throw new OrderNotValidException("Order product original price does not match the product price", ExceptionCode.UUTI_45);
        } else if (price.compareTo(originalPrice) != 0)
            throw new OrderNotValidException("Order product original price does not match the product price", ExceptionCode.UUTI_45);
    }
}
