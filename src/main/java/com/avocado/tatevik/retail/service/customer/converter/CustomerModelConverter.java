package com.avocado.tatevik.retail.service.customer.converter;

import com.avocado.tatevik.retail.controller.customer.dto.CustomerDto;
import com.avocado.tatevik.retail.repository.customer.entity.CustomerEntity;
import com.avocado.tatevik.retail.service.customer.model.CustomerCreationModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelConverter {

    public CustomerDto convert(CustomerModel model) {
        if (model == null) {
            return null;
        }
        return new CustomerDto(
                model.getId(),
                model.getName(),
                model.getPhoneNumber(),
                model.getAddressId(),
                model.getBonus());
    }

    public CustomerEntity convert(CustomerUpdateModel model, CustomerEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setName(model.getName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setBonus(model.getBonus());
        return entity;
    }

    public CustomerEntity convert(CustomerCreationModel model) {
        if (model == null) {
            return null;
        }
        CustomerEntity entity = new CustomerEntity();
        entity.setName(model.getName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setBonus(model.getBonus());
        return entity;
    }
}
