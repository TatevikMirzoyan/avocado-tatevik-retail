package com.avocado.tatevik.retail.repository.address.entity;

import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "AddressEntity")
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "address_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="address_generator", sequenceName = "address_seq")
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Country country;

    @Column
    private String district;

    @Column
    private String city;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String postCode;
}
