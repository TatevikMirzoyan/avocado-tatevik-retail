package com.avocado.tatevik.retail.controller.shop.converter;


import com.avocado.tatevik.retail.controller.shop.dto.ShopCreationDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopUpdateDto;
import com.avocado.tatevik.retail.service.shop.model.ShopCreationModel;
import com.avocado.tatevik.retail.service.shop.model.ShopModel;
import com.avocado.tatevik.retail.service.shop.model.ShopUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class ShopDtoConverter {

    public ShopModel convert(ShopDto dto) {
        if (dto == null) {
            return null;
        }
        return new ShopModel(
                dto.getId(),
                dto.getName(),
                dto.getActive(),
                dto.getVisible());
    }

    public ShopUpdateModel convert(Long id, ShopUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new ShopUpdateModel(
                id,
                dto.getName(),
                dto.getActive(),
                dto.getVisible());
    }

    public ShopCreationModel convert(ShopCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new ShopCreationModel(
                dto.getName(),
                dto.getActive(),
                dto.getVisible());
    }
}
