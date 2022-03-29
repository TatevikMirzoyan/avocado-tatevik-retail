package com.avocado.tatevik.retail.service.product.impl;

import com.avocado.tatevik.retail.repository.product.ProductRepository;
import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import com.avocado.tatevik.retail.repository.shop.ShopRepository;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import com.avocado.tatevik.retail.service.product.ProductService;
import com.avocado.tatevik.retail.service.product.converter.ProductEntityConverter;
import com.avocado.tatevik.retail.service.product.converter.ProductModelConverter;
import com.avocado.tatevik.retail.service.product.model.ProductCreationModel;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import com.avocado.tatevik.retail.service.product.model.ProductUpdateModel;
import com.avocado.tatevik.retail.service.product.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityConverter productEntityConverter;

    @Autowired
    private ProductModelConverter productModelConverter;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductValidator productValidator;

    @Override
    public ProductModel get(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product entity with id " + id + " does not exist."));
        return productEntityConverter.convert(entity);
    }

    @Override
    @Transactional
    public ProductModel create(ProductCreationModel creationModel) {
        productValidator.isValid(creationModel.getName(), creationModel.getDescription());
        ProductEntity entity = productModelConverter.convert(creationModel);
        Long shopId = creationModel.getShopId();
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop entity with id " + shopId + " does not exist."));
        entity.setShop(shop);
        entity = productRepository.save(entity);
        return productEntityConverter.convert(entity);
    }

    @Override
    public ProductModel update(ProductUpdateModel updateModel) {
        ProductEntity entity = productRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product entity with id " + updateModel.getId() + " does not exist."));
        if (nameIsChanged(entity.getName(), updateModel.getName())) {
            productValidator.isValid(updateModel.getName(), updateModel.getDescription());
        } else {
            productValidator.isValid(updateModel.getDescription());
        }
        entity = productModelConverter.convert(updateModel, entity);
        entity = productRepository.save(entity);
        return productEntityConverter.convert(entity);
    }

    @Override
    public Boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    private boolean nameIsChanged(String oldName, String newName) {
        return !oldName.equalsIgnoreCase(newName);
    }
}
