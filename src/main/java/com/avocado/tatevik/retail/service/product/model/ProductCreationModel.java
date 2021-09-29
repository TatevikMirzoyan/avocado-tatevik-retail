package com.avocado.tatevik.retail.service.product.model;

import com.avocado.tatevik.retail.common.enums.Unit;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ProductCreationModel {

    private String name;

    private String description;

    private Boolean active;

    private Boolean visible;

    private BigDecimal price;

    private Unit unit;

    private Long shopId;
}
