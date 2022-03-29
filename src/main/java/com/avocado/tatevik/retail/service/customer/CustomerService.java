package com.avocado.tatevik.retail.service.customer;

import com.avocado.tatevik.retail.repository.address.AddressRepository;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.repository.customer.CustomerRepository;
import com.avocado.tatevik.retail.repository.customer.entity.CustomerEntity;
import com.avocado.tatevik.retail.service.customer.converter.CustomerEntityConverter;
import com.avocado.tatevik.retail.service.customer.converter.CustomerModelConverter;
import com.avocado.tatevik.retail.service.customer.model.CustomerCreationModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityConverter customerEntityConverter;

    @Autowired
    private CustomerModelConverter customerModelConverter;

    @Autowired
    private AddressRepository addressRepository;

    public CustomerModel get(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer entity with id " + id + " does not exist."));
        return customerEntityConverter.convert(entity);
    }

    public CustomerModel create(CustomerCreationModel creationModel) {
        CustomerEntity entity = customerModelConverter.convert(creationModel);
        Long addressId = creationModel.getAddressId();
        if (addressId != null) {
            AddressEntity address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + addressId + " does not exist."));
            entity.setAddress(address);
        } else entity.setAddress(null);
        entity = customerRepository.save(entity);
        return customerEntityConverter.convert(entity);
    }

    public CustomerModel update(CustomerUpdateModel updateModel) {
        CustomerEntity entity = customerRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer entity with id " + updateModel.getId() + " does not exist."));
        entity = customerModelConverter.convert(updateModel, entity);
        if (updateModel.getAddressId() != null) {
            AddressEntity address = addressRepository.findById(updateModel.getAddressId())
                    .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + updateModel.getAddressId() + " does not exist."));
            entity.setAddress(address);
        } else entity.setAddress(null);
        entity = customerRepository.save(entity);
        return customerEntityConverter.convert(entity);
    }

    @Transactional
    public Boolean delete(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer entity with id " + id + " does not exist."));
        customerRepository.updateDeleted(id);
        return true;
    }
}
