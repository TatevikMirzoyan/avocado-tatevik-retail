package com.avocado.tatevik.retail.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserCRUDController {

//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/{id}")
//    public GenericResponse<ShopDto> get(@PathVariable Long id) {
//        ShopModel model = shopService.get(id);
//        ShopDto dto = shopModelConverter.convert(model);
//        return new GenericResponse<>(dto, new ErrorResponseListDto());
//    }
//
//    @PostMapping
//    public GenericResponse<ShopDto> create(@Valid @RequestBody ShopCreationDto creationDto) {
//        ShopCreationModel creationModel = shopDtoConverter.convert(creationDto);
//        ShopModel model = shopService.create(creationModel);
//        ShopDto dto = shopModelConverter.convert(model);
//        return new GenericResponse<>(dto, new ErrorResponseListDto());
//    }
//
//    @PutMapping("/{id}")
//    public GenericResponse<ShopDto> update(@PathVariable Long id, @Valid @RequestBody ShopUpdateDto updateDto) {
//        ShopUpdateModel updateModel = shopDtoConverter.convert(id, updateDto);
//        ShopModel model = shopService.update(updateModel);
//        ShopDto dto = shopModelConverter.convert(model);
//        return new GenericResponse<>(dto, new ErrorResponseListDto());
//    }
//
//    @DeleteMapping("/{id}")
//    public GenericResponse<Boolean> delete(@PathVariable Long id) {
//        Boolean isDeleted = shopService.delete(id);
//        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
//    }
}
