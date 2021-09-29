package com.avocado.tatevik.retail.controller.product.dto;

import com.avocado.tatevik.retail.common.enums.Unit;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ProductDto {

   private Long id;

   private String name;

   private String description;

   private Boolean active;

   private Boolean visible;

   private BigDecimal price;

   private Unit unit;

   private Long shopId;
}
