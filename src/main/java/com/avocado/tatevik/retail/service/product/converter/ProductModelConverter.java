package com.avocado.tatevik.retail.service.product.converter;

import com.avocado.tatevik.retail.controller.product.dto.ProductDto;
import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import com.avocado.tatevik.retail.service.product.model.ProductCreationModel;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import com.avocado.tatevik.retail.service.product.model.ProductUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class ProductModelConverter {

    public ProductDto convert(ProductModel model) {
        if (model == null) {
            return null;
        }
        return new ProductDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getActive(),
                model.getVisible(),
                model.getPrice(),
                model.getUnit(),
                model.getShopId());
    }

    public ProductEntity convert(ProductUpdateModel model, ProductEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setName(model.getName());
        entity.setActive(model.getActive());
        entity.setVisible(model.getVisible());
        entity.setPrice(model.getPrice());
        entity.setDescription(model.getDescription());
        entity.setUnit(model.getUnit());
        return entity;
    }

    public ProductEntity convert(ProductCreationModel model) {
        if (model == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setName(model.getName());
        entity.setActive(model.getActive());
        entity.setVisible(model.getVisible());
        entity.setPrice(model.getPrice());
        entity.setDescription(model.getDescription());
        entity.setUnit(model.getUnit());
        return entity;
    }
}
