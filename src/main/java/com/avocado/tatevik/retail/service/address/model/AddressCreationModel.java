package com.avocado.tatevik.retail.service.address.model;

import com.avocado.tatevik.retail.common.enums.Country;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AddressCreationModel {

    private Country country;

    private String district;

    private String city;

    private String addressLine1;

    private String addressLine2;

    private String postCode;
}
