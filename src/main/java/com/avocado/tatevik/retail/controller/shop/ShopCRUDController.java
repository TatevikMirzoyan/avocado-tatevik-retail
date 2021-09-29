package com.avocado.tatevik.retail.controller.shop;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.shop.converter.ShopDtoConverter;
import com.avocado.tatevik.retail.controller.shop.dto.ShopCreationDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopUpdateDto;
import com.avocado.tatevik.retail.service.shop.ShopService;
import com.avocado.tatevik.retail.service.shop.converter.ShopModelConverter;
import com.avocado.tatevik.retail.service.shop.model.ShopCreationModel;
import com.avocado.tatevik.retail.service.shop.model.ShopModel;
import com.avocado.tatevik.retail.service.shop.model.ShopUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shops")
public class ShopCRUDController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopDtoConverter shopDtoConverter;

    @Autowired
    private ShopModelConverter shopModelConverter;

    @GetMapping("/{id}")
    public GenericResponse<ShopDto> get(@PathVariable Long id) {
        ShopModel model = shopService.get(id);
        ShopDto dto = shopModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GenericResponse<ShopDto> create(@Valid @RequestBody ShopCreationDto creationDto) {
        ShopCreationModel creationModel = shopDtoConverter.convert(creationDto);
        ShopModel model = shopService.create(creationModel);
        ShopDto dto = shopModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<ShopDto> update(@PathVariable Long id, @Valid @RequestBody ShopUpdateDto updateDto) {
        ShopUpdateModel updateModel = shopDtoConverter.convert(id, updateDto);
        ShopModel model = shopService.update(updateModel);
        ShopDto dto = shopModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = shopService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}
