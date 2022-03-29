package com.avocado.tatevik.retail.repository.orderproduct.entity;

import com.avocado.tatevik.retail.repository.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "OrderProductEntity")
@Table(name = "order_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderProductEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "order_product_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_product_generator", sequenceName = "order_product_seq")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;

    @Column
    private BigDecimal amount;

    @Column
    private String comment;

    @Column
    private BigDecimal originalPrice;

    @Column
    private BigDecimal discount;

    @Column
    private BigDecimal totalPrice;
}
