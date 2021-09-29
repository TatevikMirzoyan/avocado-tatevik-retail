package com.avocado.tatevik.retail.service.shop.converter;

import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import com.avocado.tatevik.retail.service.shop.model.ShopModel;
import org.springframework.stereotype.Component;

@Component
public class ShopEntityConverter {

    public ShopModel convert(ShopEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ShopModel(
                entity.getId(),
                entity.getName(),
                entity.getActive(),
                entity.getVisible());
    }
}
