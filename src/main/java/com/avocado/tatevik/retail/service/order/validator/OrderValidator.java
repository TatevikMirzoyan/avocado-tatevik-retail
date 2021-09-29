package com.avocado.tatevik.retail.service.order.validator;

import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    @Autowired
    private OrderPriceValidator orderPriceValidator;

    public void isValid(OrderCreationModel creationModel) {
        orderPriceValidator.isValid(creationModel);
    }
}
