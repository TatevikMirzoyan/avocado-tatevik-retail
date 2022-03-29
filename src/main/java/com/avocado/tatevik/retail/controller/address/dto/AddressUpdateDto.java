package com.avocado.tatevik.retail.controller.address.dto;

import com.avocado.tatevik.retail.common.enums.Country;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AddressUpdateDto {

    private Long id;

    @NotNull(message = "Country can not be null")
    private Country country;

    @NotBlank(message = "Distinct can not be null")
    private String distinct;

    @NotBlank(message = "City can not be null")
    private String city;

    @NotBlank(message = "Address Line-1 can not be null")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Post Code can not be null")
    private String postCode;
}
