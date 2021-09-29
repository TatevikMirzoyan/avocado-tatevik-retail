package com.avocado.tatevik.retail.service.order.model;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderCreationModel {

    private Long customerId;

    private Long shopId;

    private Long addressId;

    private BigDecimal originalPrice;

    private BigDecimal totalPrice;

    private BigDecimal paidFromBonus;

    private BigDecimal orderDiscount;

    private PaymentType paymentType;

    private List<OrderProductCreationModel> orderProducts;
}
