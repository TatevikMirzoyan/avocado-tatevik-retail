package com.avocado.tatevik.retail.service.address.converter;

import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityConverter {

    public AddressModel convert(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AddressModel(
                entity.getId(),
                entity.getCountry(),
                entity.getDistrict(),
                entity.getCity(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getPostCode());
    }
}
