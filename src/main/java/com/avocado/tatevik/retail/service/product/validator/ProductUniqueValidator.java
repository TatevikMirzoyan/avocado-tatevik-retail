package com.avocado.tatevik.retail.service.product.validator;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.exceptions.ProductNotValidException;
import com.avocado.tatevik.retail.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniqueValidator {

    @Autowired
    private ProductRepository productRepository;

    public void isUnique(String name) {
        if (name == null ||
                (productRepository.findByName(name).isPresent())) {
            throw new ProductNotValidException(ExceptionCode.UUTI_45, "Product with name " + name + " already exist.");
        }
    }
}
