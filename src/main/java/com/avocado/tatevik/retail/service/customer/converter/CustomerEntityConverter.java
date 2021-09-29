package com.avocado.tatevik.retail.service.customer.converter;

import com.avocado.tatevik.retail.repository.customer.entity.CustomerEntity;
import com.avocado.tatevik.retail.service.customer.model.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityConverter {

    public CustomerModel convert(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CustomerModel(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getAddress() == null ? null : entity.getAddress().getId(),
                entity.getBonus());
    }
}
