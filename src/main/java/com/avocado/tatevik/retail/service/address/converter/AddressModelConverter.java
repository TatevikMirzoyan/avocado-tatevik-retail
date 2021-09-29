package com.avocado.tatevik.retail.service.address.converter;

import com.avocado.tatevik.retail.controller.address.dto.AddressDto;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.service.address.model.AddressCreationModel;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import com.avocado.tatevik.retail.service.address.model.AddressUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class AddressModelConverter {

    public AddressDto convert(AddressModel model) {
        if (model == null) {
            return null;
        }
        return new AddressDto(
                model.getId(),
                model.getCountry(),
                model.getDistrict(),
                model.getCity(),
                model.getAddressLine1(),
                model.getAddressLine2(),
                model.getPostCode());
    }

    public AddressEntity convert(AddressUpdateModel model, AddressEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setAddressLine1(model.getAddressLine1());
        entity.setAddressLine2(model.getAddressLine2());
        entity.setCountry(model.getCountry());
        entity.setCity(model.getCity());
        entity.setDistrict(model.getDistrict());
        entity.setPostCode(model.getPostCode());
        return entity;
    }

    public AddressEntity convert(AddressCreationModel model) {
        if (model == null) {
            return null;
        }
        AddressEntity entity = new AddressEntity();
        entity.setAddressLine1(model.getAddressLine1());
        entity.setAddressLine2(model.getAddressLine2());
        entity.setCountry(model.getCountry());
        entity.setCity(model.getCity());
        entity.setDistrict(model.getDistrict());
        entity.setPostCode(model.getPostCode());
        return entity;
    }
}
