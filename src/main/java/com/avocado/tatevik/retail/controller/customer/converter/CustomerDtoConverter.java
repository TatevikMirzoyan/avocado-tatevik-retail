package com.avocado.tatevik.retail.controller.customer.converter;

import com.avocado.tatevik.retail.controller.customer.dto.CustomerCreationDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerUpdateDto;
import com.avocado.tatevik.retail.service.customer.model.CustomerCreationModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerModel convert(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        return new CustomerModel(
                dto.getId(),
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getAddressId(),
                dto.getBonus());
    }

    public CustomerUpdateModel convert(Long id, CustomerUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new CustomerUpdateModel(
                id,
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getAddressId(),
                dto.getBonus());
    }

    public CustomerCreationModel convert(CustomerCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new CustomerCreationModel(
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getAddressId(),
                dto.getBonus());
    }
}
