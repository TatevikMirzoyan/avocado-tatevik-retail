package com.avocado.tatevik.retail.service.shop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopValidator {

    @Autowired
    private ShopUniqueValidator shopUniqueValidator;

    public void isValid(String name) {
        shopUniqueValidator.isUnique(name);
    }
}
