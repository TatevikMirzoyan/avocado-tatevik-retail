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

    @NotNull(message = "Product id can not be null")
    private Long productId;

    private Long orderId;

    @NotNull(message = "Amount can not be null")
    @Positive(message = "Amount can not be negative value or zero")
    private BigDecimal amount;

    private String comment;

    @NotNull(message = "Original price can not be null")
    @PositiveOrZero(message = "Original price can not be negative value")
    private BigDecimal originalPrice;

    @PositiveOrZero(message = "Discount can not be negative value")
    private BigDecimal discount;

    @NotNull(message = "Total price can not be null")
    @PositiveOrZero(message = "Total price can not be negative value")
    private BigDecimal totalPrice;
}
