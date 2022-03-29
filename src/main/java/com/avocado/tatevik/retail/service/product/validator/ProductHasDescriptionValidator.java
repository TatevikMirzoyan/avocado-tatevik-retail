package com.avocado.tatevik.retail.service.product.validator;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.exceptions.ProductNotValidException;
import org.springframework.stereotype.Component;

@Component
public class ProductHasDescriptionValidator {

    public void hasDescription(String description) {
        if (description == null || description.isBlank())
            throw new ProductNotValidException(ExceptionCode.UUTI_45, "The product description is mandatory.");
    }
}
