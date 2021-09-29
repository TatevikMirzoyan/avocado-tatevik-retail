package com.avocado.tatevik.retail.controller.orderproduct;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.orderproduct.converter.OrderProductDtoConverter;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductCreationDto;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductDto;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductUpdateDto;
import com.avocado.tatevik.retail.service.orderproduct.OrderProductService;
import com.avocado.tatevik.retail.service.orderproduct.converter.OrderProductModelConverter;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductCreationModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductModel;
import com.avocado.tatevik.retail.service.orderproduct.model.OrderProductUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order_products")
public class OrderProductCRUDController {

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private OrderProductDtoConverter orderProductDtoConverter;

    @Autowired
    private OrderProductModelConverter orderProductModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<OrderProductDto> get(@PathVariable Long id) {
        OrderProductModel model = orderProductService.get(id);
        OrderProductDto dto = orderProductModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    public GenericResponse<OrderProductDto> create(@Valid @RequestBody OrderProductCreationDto creationDto) {
        OrderProductCreationModel creationModel = orderProductDtoConverter.convert(creationDto);
        OrderProductModel model = orderProductService.create(creationModel);
        OrderProductDto dto = orderProductModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<OrderProductDto> update(@PathVariable Long id, @Valid @RequestBody OrderProductUpdateDto updateDto) {
        OrderProductUpdateModel updateModel = orderProductDtoConverter.convert(id, updateDto);
        OrderProductModel model = orderProductService.update(updateModel);
        OrderProductDto dto = orderProductModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = orderProductService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}
