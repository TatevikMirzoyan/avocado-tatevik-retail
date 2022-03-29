package com.avocado.tatevik.retail.controller.order.dto;

import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.common.enums.OrdersSearchSortField;
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

    @PositiveOrZero(message = "Total Price can not be negative value")
    private BigDecimal totalPrice;

    private Sort.Direction sortDirection;

    private OrdersSearchSortField sort;

    @PositiveOrZero(message = "Page size can not be negative value")
    private Integer maxPageSize;

    @PositiveOrZero(message = "Offset can not be negative value")
    private Integer offset;
}
