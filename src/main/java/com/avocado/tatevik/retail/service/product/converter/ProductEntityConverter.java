package com.avocado.tatevik.retail.service.product.converter;

import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter {

    public ProductModel convert(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProductModel(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getActive(),
                entity.getVisible(),
                entity.getPrice(),
                entity.getUnit(),
                entity.getShop() == null ? null : entity.getShop().getId());
    }
}
