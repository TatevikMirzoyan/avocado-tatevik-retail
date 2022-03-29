package com.avocado.tatevik.retail.repository.product.entity;

import com.avocado.tatevik.retail.common.enums.Unit;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.shop.entity.ShopEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "ProductEntity")
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "product_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_generator", sequenceName = "product_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private Boolean active;

    @Column
    private Boolean visible;

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Unit unit;

    @ManyToOne(optional = false)
    private ShopEntity shop;

    // TODO: 8/26/21 ProductSchedule
    //ProductSchedule
}
