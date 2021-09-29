package com.avocado.tatevik.retail.controller.product;

import com.avocado.tatevik.retail.controller.product.converter.ProductDtoConverter;
import com.avocado.tatevik.retail.controller.product.dto.ProductCreationDto;
import com.avocado.tatevik.retail.controller.product.dto.ProductDto;
import com.avocado.tatevik.retail.controller.product.dto.ProductUpdateDto;
import com.avocado.tatevik.retail.service.product.ProductServiceImpl;
import com.avocado.tatevik.retail.service.product.converter.ProductModelConverter;
import com.avocado.tatevik.retail.service.product.model.ProductCreationModel;
import com.avocado.tatevik.retail.service.product.model.ProductModel;
import com.avocado.tatevik.retail.service.product.model.ProductUpdateModel;
import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductCRUDController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @Autowired
    private ProductModelConverter productModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<ProductDto> get(@PathVariable(name = "id") Long id) {
        ProductModel model = productServiceImpl.get(id);
        ProductDto dto = productModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    public GenericResponse<ProductDto> create(@Valid @RequestBody ProductCreationDto productCreationDto) {
        // TODO: 9/17/21 In case when I am trying to add product with the same name but different shopId,
        //  I am getting error` "message": "Product with this name already exist."..... Is this ok???
        ProductCreationModel creationModel = productDtoConverter.convert(productCreationDto);
        ProductModel model = productServiceImpl.create(creationModel);
        ProductDto dto = productModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<ProductDto> update(@PathVariable(name = "id") Long id, @RequestBody ProductUpdateDto updateDTO) {
        ProductUpdateModel updateModel = productDtoConverter.convert(id, updateDTO);
        ProductModel model = productServiceImpl.update(updateModel);
        ProductDto dto = productModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable(name = "id") Long id) {
        Boolean isDeleted = productServiceImpl.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}
