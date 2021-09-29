package com.avocado.tatevik.retail.controller.address.dto;

import com.avocado.tatevik.retail.common.enums.Country;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AddressDto {

    private Long id;

    private Country country;

    private String distinct;

    private String city;

    private String addressLine1;

    private String addressLine2;

    private String postCode;
}
