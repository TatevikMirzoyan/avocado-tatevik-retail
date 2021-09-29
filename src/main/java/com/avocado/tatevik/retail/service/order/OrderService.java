package com.avocado.tatevik.retail.service.order;

import com.avocado.tatevik.retail.repository.address.AddressRepository;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.repository.customer.CustomerRepository;
import com.avocado.tatevik.retail.repository.customer.entity.CustomerEntity;
import com.avocado.tatevik.retail.repository.order.OrderRepository;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import com.avocado.tatevik.retail.repository.shop.ShopRepository;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import com.avocado.tatevik.retail.service.order.converter.OrderEntityConverter;
import com.avocado.tatevik.retail.service.order.converter.OrderModelConverter;
import com.avocado.tatevik.retail.service.order.model.OrderCreationModel;
import com.avocado.tatevik.retail.service.order.model.OrderModel;
import com.avocado.tatevik.retail.service.order.model.OrderUpdateModel;
import com.avocado.tatevik.retail.service.order.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEntityConverter orderEntityConverter;

    @Autowired
    private OrderModelConverter orderModelConverter;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderValidator orderValidator;

    public OrderModel get(Long id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order entity with id " + id + " does not exist."));
        return orderEntityConverter.convert(entity);
    }

    @Transactional
    public OrderModel create(OrderCreationModel creationModel) {
        orderValidator.isValid(creationModel);
        AddressEntity address = addressRepository.findById(creationModel.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address entity with id " + creationModel.getAddressId() + " does not exist."));
        CustomerEntity customer = customerRepository.findById(creationModel.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer entity with id " + creationModel.getCustomerId() + " does not exist."));
        ShopEntity shop = shopRepository.findById(creationModel.getShopId())
                .orElseThrow(() -> new EntityNotFoundException("Shop entity with id " + creationModel.getShopId() + " does not exist."));
        OrderEntity entity = orderModelConverter.convert(creationModel);
        entity.setAddress(address);
        entity.setCustomer(customer);
        entity.setShop(shop);
        entity = orderRepository.save(entity);
        return orderEntityConverter.convert(entity);
    }

    public OrderModel update(OrderUpdateModel updateModel) {
        OrderEntity entity = orderRepository.findById(updateModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order entity with id " + updateModel.getId() + " does not exist."));
        entity = orderModelConverter.convert(updateModel, entity);
        entity = orderRepository.save(entity);
        return orderEntityConverter.convert(entity);
    }

    public Boolean delete(Long id) {
        orderRepository.deleteById(id);
        return true;
    }
}

