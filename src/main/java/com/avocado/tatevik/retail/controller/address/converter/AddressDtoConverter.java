package com.avocado.tatevik.retail.controller.address.converter;

import com.avocado.tatevik.retail.controller.address.dto.AddressCreationDto;
import com.avocado.tatevik.retail.controller.address.dto.AddressDto;
import com.avocado.tatevik.retail.controller.address.dto.AddressUpdateDto;
import com.avocado.tatevik.retail.service.address.model.AddressCreationModel;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import com.avocado.tatevik.retail.service.address.model.AddressUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {

    public AddressModel convert(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        return new AddressModel(
                dto.getId(),
                dto.getCountry(),
                dto.getDistinct(),
                dto.getCity(),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPostCode());
    }

    public AddressUpdateModel convert(Long id, AddressUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new AddressUpdateModel(
                id,
                dto.getCountry(),
                dto.getDistinct(),
                dto.getCity(),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPostCode());
    }

    public AddressCreationModel convert(AddressCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new AddressCreationModel(
                dto.getCountry(),
                dto.getDistinct(),
                dto.getCity(),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPostCode());
    }
}
