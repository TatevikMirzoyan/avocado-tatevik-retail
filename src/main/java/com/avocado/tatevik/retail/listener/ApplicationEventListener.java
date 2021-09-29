package com.avocado.tatevik.retail.listener;

import com.avocado.tatevik.retail.controller.address.AddressCRUDController;
import com.avocado.tatevik.retail.controller.address.dto.AddressCreationDto;
import com.avocado.tatevik.retail.controller.address.dto.AddressDto;
import com.avocado.tatevik.retail.controller.customer.CustomerCRUDController;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerCreationDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerDto;
import com.avocado.tatevik.retail.controller.customer.dto.CustomerUpdateDto;
import com.avocado.tatevik.retail.controller.order.OrderCRUDController;
import com.avocado.tatevik.retail.controller.orderproduct.dto.OrderProductCreationDto;
import com.avocado.tatevik.retail.controller.product.ProductCRUDController;
import com.avocado.tatevik.retail.controller.product.dto.ProductCreationDto;
import com.avocado.tatevik.retail.controller.product.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.common.enums.PaymentType;
import com.avocado.tatevik.retail.common.enums.Unit;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.order.dto.OrderCreationDto;
import com.avocado.tatevik.retail.controller.order.dto.OrderDto;
import com.avocado.tatevik.retail.controller.shop.ShopCRUDController;
import com.avocado.tatevik.retail.controller.shop.dto.ShopCreationDto;
import com.avocado.tatevik.retail.controller.shop.dto.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationEventListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShopCRUDController shopController;

    @Autowired
    private ProductCRUDController productController;

    @Autowired
    private CustomerCRUDController customerController;

    @Autowired
    private AddressCRUDController addressController;

    @Autowired
    private OrderCRUDController orderController;

//   @EventListener(ContextRefreshedEvent.class)
//    public void onContextRefreshedEvent() {
//        GenericResponse<ShopDto> shop = createShop();
//        GenericResponse<ProductDto> product1 = createProduct(shop.getBody());
////        GenericResponse<ProductDto> product2 = createProduct(shop.getBody());
//        GenericResponse<CustomerDto> customer = createCustomer();
//        GenericResponse<AddressDto> address = createAddress();
//        customer = updateCustomer(customer.getBody(), address.getBody());
//        GenericResponse<OrderDto> order = createOrder(customer.getBody().getId(), address.getBody().getId(), shop.getBody().getId());
//       try {
//           String json = objectMapper.writeValueAsString(order);
//       } catch (JsonProcessingException e) {
//           e.printStackTrace();
//       }
//   }

    public GenericResponse<ShopDto> createShop() {
//      1. create shop
        ShopCreationDto shopCreationDto = new ShopCreationDto("SAS", true, true);
        return shopController.create(shopCreationDto);
    }

    public GenericResponse<ProductDto> createProduct(ShopDto shopDto) {
//      2. create product
        ProductCreationDto productCreationDTO =
                new ProductCreationDto("Burger", "Burger with steak", true,
                        true, new BigDecimal(1000), Unit.PIECE, shopDto.getId());

        return productController.create(productCreationDTO);
    }

    public GenericResponse<CustomerDto> createCustomer() {
//      3. create customer
        CustomerCreationDto customerCreationDto =
                new CustomerCreationDto("Tatev", "132456789", null, new BigDecimal(10));

        return customerController.create(customerCreationDto);
    }

    public GenericResponse<AddressDto> createAddress() {
//      4. create address
        AddressCreationDto addressCreationDto = new AddressCreationDto(Country.ARMENIA, "Ararat",
                "Aygavan", "MyStreet", "14", "0604");

        return addressController.create(addressCreationDto);
    }

    public GenericResponse<CustomerDto> updateCustomer(CustomerDto customerDto, AddressDto addressDto) {
//        5. update customer with address
        CustomerUpdateDto updateCustomerDto = new CustomerUpdateDto(customerDto.getId(), customerDto.getName(),
                customerDto.getPhoneNumber(), addressDto.getId(), customerDto.getBonus());

        return customerController.update(updateCustomerDto.getId(), updateCustomerDto);
    }

    public GenericResponse<OrderDto> createOrder(Long customerId, Long addressId, Long shopId) {
//      6. create order
        OrderCreationDto orderCreationDto = new OrderCreationDto(customerId,
                shopId, addressId, new BigDecimal(1000), new BigDecimal(50),
                new BigDecimal(950), new BigDecimal(50), PaymentType.CARD, null);

        orderCreationDto.setOrderProducts(createOrderProductList());
        return orderController.create(orderCreationDto);
    }


    private List<OrderProductCreationDto> createOrderProductList() {
//        what about creating orderProducts???
        List<OrderProductCreationDto> orderProductCreationDtos = new ArrayList<>();
        OrderProductCreationDto dto1 = new OrderProductCreationDto(1L, null,
                new BigDecimal(5), "comment", new BigDecimal(1000), new BigDecimal(10), new BigDecimal(900));

        OrderProductCreationDto dto2 = new OrderProductCreationDto(1L, null,
                new BigDecimal(10), "MyComment", new BigDecimal(2000), new BigDecimal(10), new BigDecimal(1800));
        orderProductCreationDtos.add(dto1);
        orderProductCreationDtos.add(dto2);
        return orderProductCreationDtos;
    }
}
