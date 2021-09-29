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
public class ProductUpdateDto {

    private Long id;

    @NotBlank(message = "Product name could not be null")
    private String name;

    @NotBlank(message = "Product description could not be null")
    private String description;

    @NotNull(message = "Product active field could not be null")
    private Boolean active;

    @NotNull(message = "Product visible field could not be null")
    private Boolean visible;

    @NotNull(message = "Product price could not be null")
    @PositiveOrZero(message = "Product price could not be negative value")
    private BigDecimal price;

    @NotNull(message = "Product unit could not be null")
    private Unit unit;

    @NotNull(message = "Shop id could not be null")
    private Long shopId;
}
