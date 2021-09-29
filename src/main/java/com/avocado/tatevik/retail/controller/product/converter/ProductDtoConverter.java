package com.avocado.tatevik.retail.controller.product.converter;

import com.avocado.tatevik.retail.controller.product.dto.ProductCreationDto;
import com.avocado.tatevik.retail.controller.product.dto.ProductDto;
import com.avocado.tatevik.retail.controller.product.dto.ProductUpdateDto;
import com.avocado.tatevik.retail.service.product.model.ProductCreationModel;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import com.avocado.tatevik.retail.service.product.model.ProductUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter {

    public ProductModel convert(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        return new ProductModel(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getActive(),
                dto.getVisible(),
                dto.getPrice(),
                dto.getUnit(),
                dto.getShopId());
    }

    public ProductUpdateModel convert(Long id, ProductUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new ProductUpdateModel(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getActive(),
                dto.getVisible(),
                dto.getPrice(),
                dto.getUnit(),
                dto.getShopId());
    }

    public ProductCreationModel convert(ProductCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new ProductCreationModel(
                dto.getName(),
                dto.getDescription(),
                dto.getActive(),
                dto.getVisible(),
                dto.getPrice(),
                dto.getUnit(),
                dto.getShopId());
    }
}
