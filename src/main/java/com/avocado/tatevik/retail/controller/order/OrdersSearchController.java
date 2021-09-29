package com.avocado.tatevik.retail.controller.order;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.order.dto.OrderDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderSearchQueryParams;
import com.avocado.tatevik.retail.service.order.OrdersSearchService;
import com.avocado.tatevik.retail.service.order.converter.OrderModelConverter;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class OrdersSearchController {

    @Autowired
    private OrdersSearchService ordersSearchService;

    @Autowired
    private OrderModelConverter orderModelConverter;

    @GetMapping("/orders/list")
    public GenericResponse<Set<OrderDto>> search(@Valid OrderSearchQueryParams request) {
        Set<OrderModel> models = ordersSearchService.search(request);
        Set<OrderDto> response = models
                        .stream()
                        .map(orderModel -> orderModelConverter.convert(orderModel))
                        .collect(Collectors.toSet());
        return new GenericResponse<>(response, new ErrorResponseListDto());
    }
}
