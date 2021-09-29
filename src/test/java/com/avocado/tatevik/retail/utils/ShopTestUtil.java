package com.avocado.tatevik.retail.utils;

import com.avocado.tatevik.retail.controller.shop.dto.ShopCreationDto;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ShopTestUtil {

    static ShopCreationDto shop1 = new ShopCreationDto("SAS", true, true);
    static ShopCreationDto shop4 = new ShopCreationDto("", false, false);
    static ShopCreationDto shop2 = new ShopCreationDto(null, true, true);
    static ShopCreationDto shop3 = new ShopCreationDto(null, null, null);

    private static Stream<Arguments> providedShopCreationDtos() {
        return Stream.of(
                Arguments.of(shop1),
                Arguments.of(shop2),
                Arguments.of(shop3),
                Arguments.of(shop4)
        );
    }
}
