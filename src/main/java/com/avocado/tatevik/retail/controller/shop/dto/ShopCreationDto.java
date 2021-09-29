package com.avocado.tatevik.retail.controller.shop.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ShopCreationDto {

    @NotBlank(message = "Shop name could not be null")
    private String name;

    @NotNull(message = "Shop active field could not be null")
    private Boolean active;

    @NotNull(message = "Shop visible field could not be null")
    private Boolean visible;

    //Schedule

    //User
}
