package com.avocado.tatevik.retail.service.order.validator;

import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderPriceValidator {

    @Autowired
    OrderOriginalPriceValidator originalPriceValidator;

    @Autowired
    OrderTotalPriceValidator totalPriceValidator;

    public void isValid(OrderCreationModel creationModel) {
        originalPriceValidator.isValid(creationModel.getOriginalPrice(), creationModel.getOrderProducts());
        totalPriceValidator.validateTotalPrice(creationModel.getTotalPrice(), creationModel.getOriginalPrice(), creationModel.getOrderDiscount(), creationModel.getPaidFromBonus());
    }
}
