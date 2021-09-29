package com.avocado.tatevik.retail.controller.order.dto;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderUpdateDto {

    private Long id;

    @NotNull(message = "Customer id could not be null")
    private Long customerId;

    @NotNull(message = "Shop id could not be null")
    private Long shopId;

    @NotNull(message = "Address id could not be null")
    private Long addressId;

    @NotNull(message = "Original Price could not be null")
    @PositiveOrZero(message = "Original price could not be negative value")
    private BigDecimal originalPrice;

    @NotNull(message = "Total Price could not be null")
    @PositiveOrZero(message = "Total price could not be negative value")
    private BigDecimal totalPrice;

    @PositiveOrZero(message = "Paid from bonus could not be negative value")
    private BigDecimal paidFromBonus;

    @PositiveOrZero(message = "Order discount could not be negative value")
    private BigDecimal orderDiscount;

    @NotNull(message = "Payment Type could not be null")
    private PaymentType paymentType;
}
