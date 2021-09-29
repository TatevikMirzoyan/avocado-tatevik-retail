package com.avocado.tatevik.retail.service.product.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    @Autowired
    private ProductUniqueValidator uniqueValidator;

    @Autowired
    private ProductHasDescriptionValidator hasDescriptionValidator;

    public void isValid(String name, String description) {
        uniqueValidator.isUnique(name);
        hasDescriptionValidator.hasDescription(description);
    }

    public void isValid(String description) {
        hasDescriptionValidator.hasDescription(description);
    }
}
