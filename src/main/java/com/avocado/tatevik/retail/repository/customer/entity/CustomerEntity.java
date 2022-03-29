package com.avocado.tatevik.retail.repository.customer.entity;

import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.repository.order.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "CustomerEntity")
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "customer_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(nullable = false)
    private BigDecimal bonus;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;
}
