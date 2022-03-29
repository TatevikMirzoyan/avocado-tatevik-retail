package com.avocado.tatevik.retail.controller.order.dto;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductCreationDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationDto {

    @NotNull(message = "Customer id can not be null")
    private Long customerId;

    @NotNull(message = "Shop id can not be null")
    private Long shopId;

    @NotNull(message = "Address id can not be null")
    private Long addressId;

    @NotNull(message = "Original Price can not be null")
    @PositiveOrZero(message = "Original Price can not be negative value")
    private BigDecimal originalPrice;

    @NotNull(message = "Total Price can not be null")
    @PositiveOrZero(message = "Total Price can be negative value")
    private BigDecimal totalPrice;

    @PositiveOrZero(message = "Paid from Bonus can not be negative value")
    private BigDecimal paidFromBonus;

    @PositiveOrZero(message = "Order discount can not be negative value")
    private BigDecimal orderDiscount;

    @NotNull(message = "Payment Type can not be null")
    private PaymentType paymentType;

    @NotNull(message = "Order Products can not be null")
    @Size(message = "Order Products can not be null", min = 1)
    private List<@Valid OrderProductCreationDto> orderProducts;
}
