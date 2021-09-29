package com.avocado.tatevik.retail.service.shop;

import com.avocado.tatevik.retail.repository.shop.ShopRepository;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import com.avocado.tatevik.retail.service.shop.converter.ShopEntityConverter;
import com.avocado.tatevik.retail.service.shop.converter.ShopModelConverter;
import com.avocado.tatevik.retail.service.shop.model.ShopCreationModel;
import com.avocado.tatevik.retail.service.shop.model.ShopModel;
import com.avocado.tatevik.retail.service.shop.model.ShopUpdateModel;
import com.avocado.tatevik.retail.service.shop.validator.ShopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopModelConverter shopModelConverter;

    @Autowired
    private ShopEntityConverter shopEntityConverter;

    @Autowired
    private ShopValidator shopValidator;

    public ShopModel get(Long id) {
        ShopEntity entity = shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop entity with id " + id + " does not exist."));
        return shopEntityConverter.convert(entity);
    }

    public ShopModel create(ShopCreationModel creationModel) {
        shopValidator.isValid(creationModel.getName());
        ShopEntity entity = shopModelConverter.convert(creationModel);
        entity = shopRepository.save(entity);
        return shopEntityConverter.convert(entity);
    }

    public ShopModel update(ShopUpdateModel updateModel) {
        ShopEntity entity = shopRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Shop entity with id " + updateModel.getId() + " does not exist."));
        if (nameIsChanged(entity.getName(), updateModel.getName())) {
            shopValidator.isValid(updateModel.getName());
        }
        entity = shopModelConverter.convert(updateModel, entity);
        entity = shopRepository.save(entity);
        return shopEntityConverter.convert(entity);
    }

    @Transactional
    public Boolean delete(Long id) {
        shopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shop entity with id " + id + " does not exist."));
        shopRepository.updateDeleted(id);
        return true;
    }

    private boolean nameIsChanged(String oldName, String newName) {
        return !oldName.equalsIgnoreCase(newName);
    }
}
