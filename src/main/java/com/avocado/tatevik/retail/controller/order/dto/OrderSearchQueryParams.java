package com.avocado.tatevik.retail.controller.order.dto;

import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.common.enums.OrderQuerySortField;
import com.avocado.tatevik.retail.common.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderSearchQueryParams {

    private String customerName;

    private String shopName;

    private PaymentType paymentType;

    private String productName;

    private Country country;

    private String district;

    private String city;

    @PositiveOrZero(message = "Total Price must not be negative")
    private BigDecimal totalPrice;

    private Sort.Direction sortDirection;

    private OrderQuerySortField sort;

    @PositiveOrZero(message = "Page size must not be negative")
    private Integer maxPageSize;

    @PositiveOrZero(message = "Page size must not be negative")
    private Integer offset;
}
