package com.avocado.tatevik.retail.controller.orderproduct.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderProductUpdateDto {

    private Long id;

    @NotNull(message = "Product id could not be null")
    private Long productId;

    @NotNull(message = "Order id could not be null")
    private Long orderId;

    @NotNull(message = "Amount could not be null")
    @PositiveOrZero(message = "Amount could not be negative value")
    private BigDecimal amount;

    private String comment;

    @NotNull(message = "Original Price could not be null")
    @PositiveOrZero(message = "Original Price could not be negative value")
    private BigDecimal originalPrice;

    @PositiveOrZero(message = "Discount could not be negative value")
    private BigDecimal discount;

    @NotNull(message = "Total Price could not be null")
    @PositiveOrZero(message = "Total Price could not be negative value")
    private BigDecimal totalPrice;
}
