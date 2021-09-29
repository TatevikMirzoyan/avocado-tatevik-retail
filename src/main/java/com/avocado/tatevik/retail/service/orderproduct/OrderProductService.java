package com.avocado.tatevik.retail.service.orderproduct;

import com.avocado.tatevik.retail.repository.orderproduct.OrderProductRepository;
import com.avocado.tatevik.retail.repository.orderproduct.entity.OrderProductEntity;
import com.avocado.tatevik.retail.service.orderproduct.converter.OrderProductEntityConverter;
import com.avocado.tatevik.retail.service.orderproduct.converter.OrderProductModelConverter;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductUpdateModel;
import com.avocado.tatevik.retail.service.orderproduct.validator.OrderProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderProductEntityConverter orderProductEntityConverter;

    @Autowired
    private OrderProductModelConverter orderProductModelConverter;

    @Autowired
    private OrderProductValidator orderProductValidator;

    public OrderProductModel get(Long id) {
        OrderProductEntity entity = orderProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderProduct entity with id " + id + " does not exist."));
        return orderProductEntityConverter.convert(entity);
    }

    public OrderProductModel create(OrderProductCreationModel creationModel) {
        OrderProductEntity entity = orderProductModelConverter.convert(creationModel);
        entity = orderProductRepository.save(entity);
        return orderProductEntityConverter.convert(entity);
    }

    public OrderProductModel update(OrderProductUpdateModel updateModel) {
        OrderProductEntity entity = orderProductRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("OrderProduct entity with id " + updateModel.getId() + " does not exist."));
        entity = orderProductModelConverter.convert(updateModel, entity);
        entity = orderProductRepository.save(entity);
        return orderProductEntityConverter.convert(entity);
    }

    public List<OrderProductModel> saveAll(List<OrderProductCreationModel> creationModels) {
        creationModels
                .stream()
                .filter(Objects::nonNull)
                .forEach(creationModel -> orderProductValidator.isValid(creationModel));

        List<OrderProductEntity> entityList = orderProductModelConverter.convert(creationModels);
        entityList = orderProductRepository.saveAll(entityList);
        return orderProductEntityConverter.convert(entityList);
    }

    public Boolean delete(Long id) {
        orderProductRepository.deleteById(id);
        return true;
    }
}
