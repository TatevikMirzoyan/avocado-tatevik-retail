package com.avocado.tatevik.retail.controller.order;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.order.converter.OrderDtoConverter;
import com.avocado.tatevik.retail.controller.order.dto.OrderCreationDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderUpdateDto;
import com.avocado.tatevik.retail.facade.OrderFacade;
import com.avocado.tatevik.retail.service.order.OrderService;
import com.avocado.tatevik.retail.service.order.converter.OrderModelConverter;
import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.order.model.OrderUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderCRUDController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderDtoConverter orderDtoConverter;

    @Autowired
    private OrderModelConverter orderModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<OrderDto> get(@PathVariable Long id) {
        OrderModel orderModel = orderService.get(id);
        OrderDto dto = orderModelConverter.convert(orderModel);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    public GenericResponse<OrderDto> create(@Valid @RequestBody OrderCreationDto creationDto) {
        OrderCreationModel creationModel = orderDtoConverter.convert(creationDto);
        OrderModel orderModel = orderFacade.createOrder(creationModel);
        OrderDto dto = orderModelConverter.convert(orderModel);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<OrderDto> update(@PathVariable Long id, @Valid @RequestBody OrderUpdateDto updateDto) {
        OrderUpdateModel updateModel = orderDtoConverter.convert(id, updateDto);
        OrderModel orderModel = orderService.update(updateModel);
        OrderDto dto = orderModelConverter.convert(orderModel);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = orderService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}
