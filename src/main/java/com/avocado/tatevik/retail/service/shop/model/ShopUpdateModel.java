package com.avocado.tatevik.retail.service.shop.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ShopUpdateModel {

    Long id;

    String name;

    Boolean active;

    Boolean visible;

    //Schedule

    //User
}
