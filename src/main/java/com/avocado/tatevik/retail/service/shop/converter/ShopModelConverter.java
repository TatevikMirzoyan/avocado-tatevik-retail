package com.avocado.tatevik.retail.service.shop.converter;

import com.avocado.tatevik.retail.controller.shop.dto.ShopDto;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import com.avocado.tatevik.retail.service.shop.model.ShopCreationModel;
import com.avocado.tatevik.retail.service.shop.model.ShopModel;
import com.avocado.tatevik.retail.service.shop.model.ShopUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class ShopModelConverter {

    public ShopDto convert(ShopModel model) {
        if (model == null) {
            return null;
        }
        return new ShopDto(
                model.getId(),
                model.getName(),
                model.getActive(),
                model.getVisible());
    }

    public ShopEntity convert(ShopUpdateModel model, ShopEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setName(model.getName());
        entity.setActive(model.getActive());
        entity.setVisible(model.getVisible());
        return entity;
    }

    public ShopEntity convert(ShopCreationModel model) {
        if (model == null) {
            return null;
        }
        ShopEntity entity = new ShopEntity();
        entity.setName(model.getName());
        entity.setActive(model.getActive());
        entity.setVisible(model.getVisible());
        return entity;
    }
}
