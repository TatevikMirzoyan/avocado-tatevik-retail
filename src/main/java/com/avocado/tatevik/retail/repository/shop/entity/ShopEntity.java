package com.avocado.tatevik.retail.repository.shop.entity;

import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.product.entity.ProductEntity;
import com.avocado.tatevik.retail.repository.shop.converter.BooleanToIntegerConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ShopEntity")
@Table(name = "shops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "shop_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="shop_generator", sequenceName = "shop_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Convert(converter = BooleanToIntegerConverter.class)
    private Boolean active;

    @Column
    private Boolean visible;

    @OneToMany(mappedBy = "shop", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    // TODO: 8/25/21 Add Schedule field
    //Schedule

    // TODO: 8/25/21 Add User field
    //User
}
