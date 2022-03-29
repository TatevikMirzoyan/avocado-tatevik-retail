package com.avocado.tatevik.retail.controller.customer.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerUpdateDto {

    private Long id;

    @NotBlank(message = "Name can not be null")
    private String name;

    @NotBlank(message = "Name can not be null") //here we can set pattern
    private String phoneNumber;

    private Long addressId;

    @PositiveOrZero(message = "Bonus can not be negative value")
    private BigDecimal bonus;
}
