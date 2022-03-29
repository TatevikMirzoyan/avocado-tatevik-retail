package com.avocado.tatevik.retail.controller.address.dto;

import com.avocado.tatevik.retail.common.enums.Country;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddressCreationDto {

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
