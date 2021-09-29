package com.avocado.tatevik.retail.facade;

import com.avocado.tatevik.retail.service.order.OrderService;
import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.orderproduct.OrderProductService;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @Transactional
    public OrderModel createOrder(OrderCreationModel creationModel) {
        OrderModel orderModel = orderService.create(creationModel);
        creationModel.getOrderProducts()
                .stream()
                .filter(Objects::nonNull)
                .forEach(opcm -> opcm.setOrderId(orderModel.getId()));
        List<OrderProductModel> orderProducts = orderProductService.saveAll(creationModel.getOrderProducts());
        orderModel.setOrderProducts(orderProducts);
        return orderModel;
    }
}