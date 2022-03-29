package com.avocado.tatevik.retail.controller.order.dto;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderUpdateDto {

    private Long id;

    @NotNull(message = "Customer id can not be null")
    private Long customerId;

    @NotNull(message = "Shop id can not be null")
    private Long shopId;

    @NotNull(message = "Address id can not be null")
    private Long addressId;

    @NotNull(message = "Original Price can not be null")
    @PositiveOrZero(message = "Original price can not be negative value")
    private BigDecimal originalPrice;

    @NotNull(message = "Total Price can not be null")
    @PositiveOrZero(message = "Total price can not be negative value")
    private BigDecimal totalPrice;

    @PositiveOrZero(message = "Paid from bonus can not be negative value")
    private BigDecimal paidFromBonus;

    @PositiveOrZero(message = "Order discount can not be negative value")
    private BigDecimal orderDiscount;

    @NotNull(message = "Payment Type can not be null")
    private PaymentType paymentType;
}
