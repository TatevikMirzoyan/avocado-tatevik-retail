package com.avocado.tatevik.retail.service.shop.validator;

import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.shop.ShopNotValidException;
import com.avocado.tatevik.retail.repository.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopUniqueValidator {

    @Autowired
    private ShopRepository shopRepository;

    public void isUnique(String name){
        if (name == null ||
                (shopRepository.findByName(name).isPresent())) {
            throw new ShopNotValidException(ExceptionCode.UUTI_45, "Shop with this name already exist.");
        }
    }
}
