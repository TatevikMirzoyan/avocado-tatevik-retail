package com.avocado.tatevik.retail.controller.product.dto;

import com.avocado.tatevik.retail.common.enums.Unit;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {

    private Long id;

    @NotBlank(message = "Product name can not be null")
    private String name;

    @NotBlank(message = "Product description can not be null")
    private String description;

    @NotNull(message = "Product active field can not be null")
    private Boolean active;

    @NotNull(message = "Product visible field can not be null")
    private Boolean visible;

    @NotNull(message = "Product price can not be null")
    @PositiveOrZero(message = "Product price can not be negative value")
    private BigDecimal price;

    @NotNull(message = "Product unit can not be null")
    private Unit unit;

    @NotNull(message = "Shop id can not be null")
    private Long shopId;
}
