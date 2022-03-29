package com.avocado.tatevik.retail.service.address;

import com.avocado.tatevik.retail.repository.address.AddressRepository;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.service.address.converter.AddressEntityConverter;
import com.avocado.tatevik.retail.service.address.converter.AddressModelConverter;
import com.avocado.tatevik.retail.service.address.model.AddressCreationModel;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import com.avocado.tatevik.retail.service.address.model.AddressUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressEntityConverter addressEntityConverter;

    @Autowired
    private AddressModelConverter addressModelConverter;

    public AddressModel get(Long id) {
        AddressEntity entity = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + id + " does not exist."));
        return addressEntityConverter.convert(entity);
    }

    public AddressModel create(AddressCreationModel creationModel) {
        AddressEntity entity = addressModelConverter.convert(creationModel);
        entity = addressRepository.save(entity);
        return addressEntityConverter.convert(entity);
    }

    public AddressModel update(AddressUpdateModel updateModel) {
        AddressEntity entity = addressRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + updateModel.getId() + " does not exist."));
        entity = addressModelConverter.convert(updateModel, entity);
        entity = addressRepository.save(entity);
        return addressEntityConverter.convert(entity);
    }

    @Transactional
    public Boolean delete(Long id) {
        addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + id + " does not exist."));
        addressRepository.updateDeleted(id);
        return true;
    }
}
