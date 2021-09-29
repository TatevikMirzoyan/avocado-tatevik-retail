package com.avocado.tatevik.retail.service.order.model;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderUpdateModel {

    private Long id;

    private Long customerId;

    private Long shopId;

    private Long addressId;

    private BigDecimal originalPrice;

    private BigDecimal totalPrice;

    private BigDecimal paidFromBonus;

    private BigDecimal orderDiscount;

    private PaymentType paymentType;
}
