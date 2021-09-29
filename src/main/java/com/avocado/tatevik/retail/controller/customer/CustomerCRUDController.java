package com.avocado.tatevik.retail.controller.customer;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.customer.converter.CustomerDtoConverter;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerCreationDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerUpdateDto;
import com.avocado.tatevik.retail.service.customer.CustomerService;
import com.avocado.tatevik.retail.service.customer.converter.CustomerModelConverter;
import com.avocado.tatevik.retail.service.customer.model.CustomerCreationModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerModel;
import com.avocado.tatevik.retail.service.customer.model.CustomerUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerCRUDController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDtoConverter customerDtoConverter;

    @Autowired
    private CustomerModelConverter customerModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<CustomerDto> get(@PathVariable Long id) {
        CustomerModel model = customerService.get(id);
        CustomerDto dto = customerModelConverter.convert(model);
        return new GenericResponse<CustomerDto>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    public GenericResponse<CustomerDto> create(@Valid @RequestBody CustomerCreationDto creationDto) {
        CustomerCreationModel creationModel = customerDtoConverter.convert(creationDto);
        CustomerModel model = customerService.create(creationModel);
        CustomerDto dto = customerModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<CustomerDto> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateDto updateDto) {
        CustomerUpdateModel updateModel = customerDtoConverter.convert(id, updateDto);
        CustomerModel model = customerService.update(updateModel);
        CustomerDto dto = customerModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = customerService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}
