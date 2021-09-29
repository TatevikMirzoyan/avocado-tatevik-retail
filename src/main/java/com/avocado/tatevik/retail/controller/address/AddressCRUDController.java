package com.avocado.tatevik.retail.controller.address;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.address.converter.AddressDtoConverter;
import com.avocado.tatevik.retail.controller.address.dto.AddressCreationDto;
import com.avocado.tatevik.retail.controller.address.dto.AddressDto;
import com.avocado.tatevik.retail.controller.address.dto.AddressUpdateDto;
import com.avocado.tatevik.retail.service.address.AddressService;
import com.avocado.tatevik.retail.service.address.converter.AddressModelConverter;
import com.avocado.tatevik.retail.service.address.model.AddressCreationModel;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import com.avocado.tatevik.retail.service.address.model.AddressUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressCRUDController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressDtoConverter addressDtoConverter;

    @Autowired
    private AddressModelConverter addressModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<AddressDto> get(@PathVariable Long id) {
        AddressModel model = addressService.get(id);
        AddressDto dto = addressModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GenericResponse<AddressDto> create(@Valid @RequestBody AddressCreationDto creationDto) {
        AddressCreationModel creationModel = addressDtoConverter.convert(creationDto);
        AddressModel model = addressService.create(creationModel);
        AddressDto dto = addressModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<AddressDto> update(@PathVariable Long id, @Valid @RequestBody AddressUpdateDto updateDto) {
        AddressUpdateModel updateModel = addressDtoConverter.convert(id, updateDto);
        AddressModel model = addressService.update(updateModel);
        AddressDto dto = addressModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = addressService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}

