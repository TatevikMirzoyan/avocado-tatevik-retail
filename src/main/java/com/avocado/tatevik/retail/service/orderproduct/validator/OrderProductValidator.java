package com.avocado.tatevik.retail.service.orderproduct.validator;

import com.avocado.tatevik.retail.common.exception.order.OrderNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import com.avocado.tatevik.retail.repository.product.ProductRepository;
import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class OrderProductValidator {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceValidator priceValidator;

    public void isValid(OrderProductCreationModel creationModel) {
        Long productId = creationModel.getProductId();
        if (productId == null) {
            throw new OrderNotValidException("Product id must not be null", ExceptionCode.UUTI_45);
        }
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with given id " + productId + " does not exist"));

       priceValidator.isValid(productEntity.getPrice(), creationModel.getTotalPrice(), creationModel.getOriginalPrice(),
               creationModel.getDiscount(), creationModel.getAmount());
    }
}
