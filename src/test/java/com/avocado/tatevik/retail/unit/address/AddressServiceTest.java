package com.avocado.tatevik.retail.unit.address;

import com.avocado.tatevik.retail.common.enums.Country;
import com.avocado.tatevik.retail.repository.address.AddressRepository;
import com.avocado.tatevik.retail.repository.address.entity.AddressEntity;
import com.avocado.tatevik.retail.service.address.AddressService;
import com.avocado.tatevik.retail.service.address.converter.AddressEntityConverter;
import com.avocado.tatevik.retail.service.address.converter.AddressModelConverter;
import com.avocado.tatevik.retail.service.address.model.AddressCreationModel;
import com.avocado.tatevik.retail.service.address.model.AddressModel;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressEntityConverter addressEntityConverter;

    @Mock
    private AddressModelConverter addressModelConverter;

    @Before
    public void setUp() {
        // In the beforeEach  create some entities, run tests on them, and in the afterEach method delete them from db
    }

    @Test
    void get() {
        AddressEntity entity = new AddressEntity(1L, Country.ARMENIA, "Kond", "Yerevan", "Dzorap 70/3", null, "0123");
        AddressModel model = new AddressModel(1L, Country.ARMENIA, "Kond", "Yerevan", "Dzorap 70/3", null, "0123");

        when(addressRepository.findById(1L)).thenReturn(java.util.Optional.of(entity));
        when(addressEntityConverter.convert(entity)).thenReturn(model);

        model = addressService.get(1L);
        Assertions.assertEquals(entity.getDistrict(), model.getDistrict());
    }

    @Test
    void create() {
        AddressCreationModel addressCreationModel = new AddressCreationModel(Country.ARMENIA, "Ararat", "Aygavan", "Azatamatrikner 14", null, "0604");
        AddressEntity addressEntity = new AddressEntity(1L, Country.ARMENIA, "Ararat", "Aygavan", "Azatamartikner 14", null, "0604");
        AddressModel model = new AddressModel(1L, Country.ARMENIA, "Ararat", "Aygavan", "Azatamartikner 14", null, "0604");

        when(addressRepository.save(any(AddressEntity.class))).thenReturn(addressEntity);
        when(addressModelConverter.convert(addressCreationModel)).thenReturn(addressEntity);
        when(addressEntityConverter.convert(addressEntity)).thenReturn(model);

        AddressModel addressModel = addressService.create(addressCreationModel);
        Assertions.assertEquals(addressCreationModel.getDistrict(), addressModel.getDistrict());
        Assertions.assertNotNull(addressModel.getId());
    }
}