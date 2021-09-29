package com.avocado.tatevik.retail.controller.orderproduct.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCreationDto {

    @NotNull(message = "Product id could not be null")
    private Long productId;

    @NotNull(message = "Order id could not be null")
    private Long orderId;

    @NotNull(message = "Amount could not be null")
    @Positive(message = "Amount could not be negative value or zero")
    private BigDecimal amount;

    private String comment;

    @NotNull(message = "Original Price could not be null")
    @PositiveOrZero(message = "Original price could not be negative value")
    private BigDecimal originalPrice;

    @PositiveOrZero(message = "Discount could not be negative value")
    private BigDecimal discount;

    @NotNull(message = "Total Price could not be null")
    @PositiveOrZero(message = "Total price could not be negative value")
    private BigDecimal totalPrice;
}
