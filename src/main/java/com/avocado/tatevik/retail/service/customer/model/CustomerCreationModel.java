package com.avocado.tatevik.retail.service.customer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerCreationModel {

    private String name;

    private String phoneNumber;

    private Long addressId;

    private BigDecimal bonus;
}