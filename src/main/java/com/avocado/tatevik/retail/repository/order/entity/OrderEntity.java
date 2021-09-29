package com.avocado.tatevik.retail.repository.order.entity;

import com.avocado.tatevik.retail.common.enums.PaymentType;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.repository.customer.entity.CustomerEntity;
import com.avocado.tatevik.retail.repository.orderproduct.entity.OrderProductEntity;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "OrderEntity")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "order_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="order_generator", sequenceName = "order_seq")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderProductEntity> orderProducts;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "shop_id")
    private ShopEntity shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column
    private BigDecimal originalPrice;

    @Column
    private BigDecimal totalPrice;

    @Column
    private BigDecimal paidFromBonus;

    @Column
    private BigDecimal orderDiscount;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
}
