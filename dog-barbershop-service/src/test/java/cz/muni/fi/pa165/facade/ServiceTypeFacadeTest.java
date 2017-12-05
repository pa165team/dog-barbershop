package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeCreateDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.DogBarbershopServiceException;
import cz.muni.fi.pa165.service.ServiceTypeService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class ServiceTypeFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceTypeService serviceMock;
    private ServiceType typeMock;
    private static final Long ID = 42L;

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void setupEvery() {
        serviceMock = mock(ServiceTypeService.class);
        typeMock = mock(ServiceType.class);
    }

    @Test
    public void changeDescription() {
        final String newDescription = "New description";
        DescriptionChangeDTO dto = new DescriptionChangeDTO();
        dto.setServiceTypeId(ID);
        dto.setDescription(newDescription);

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock, beanMappingService);
        when(serviceMock.findById(ID)).thenReturn(typeMock);

        facade.changeDescription(dto);

        verify(typeMock).setDescription(newDescription);
    }

    @Test
    public void changePricePerHour() {
        final BigDecimal newPrice = new BigDecimal("42");

        PricePerHourChangeDTO dto = new PricePerHourChangeDTO();
        dto.setServiceTypeId(ID);
        dto.setPricePerHour(newPrice);

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock, beanMappingService);
        when(serviceMock.findById(ID)).thenReturn(typeMock);

        facade.changePricePerHour(dto);

        verify(typeMock).setPricePerHour(newPrice);
    }

    @Test
    public void cannotChangePricePerHourToLowerThanZero() {
        final BigDecimal newPrice = new BigDecimal("-42");

        PricePerHourChangeDTO dto = new PricePerHourChangeDTO();
        dto.setServiceTypeId(ID);
        dto.setPricePerHour(newPrice);

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock, beanMappingService);
        when(serviceMock.findById(ID)).thenReturn(typeMock);

        try {
            facade.changePricePerHour(dto);
            Assert.fail("It should throw DogBarbershopServiceException");
        } catch (DogBarbershopServiceException ex) {

        }
        verifyZeroInteractions(typeMock);
    }

    @Test
    public void testCreation() {
        String name = "Name";
        String description = "Description";
        BigDecimal pricePerHour = new BigDecimal("42");

        ServiceTypeCreateDTO dto = new ServiceTypeCreateDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPricePerHour(pricePerHour);

        ServiceType typeMock = mock(ServiceType.class);
        ServiceType type = new ServiceType();
        type.setName(name);
        type.setDescription(description);
        type.setPricePerHour(pricePerHour);
        when(serviceMock.create(type)).thenReturn(typeMock);
        when(typeMock.getId()).thenReturn(ID);

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock, beanMappingService);
        Long id = facade.createServiceType(dto);

        verify(serviceMock).create(type);
        Assert.assertEquals(ID, id);
    }

    @Test
    public void getAllServiceTypes() throws NoSuchFieldException, IllegalAccessException {
        // create first type and DTO for that type
        long id1 = 1L;
        String name1 = "Jméno 1";
        String description1 = "Popis 1";
        BigDecimal price1 = new BigDecimal("42");

        ServiceType type1 = new ServiceType(name1, description1, price1);

        ServiceTypeDTO dto1 = new ServiceTypeDTO();
        dto1.setId(id1);
        dto1.setName(name1);
        dto1.setDescription(description1);
        dto1.setPricePerHour(price1);

        // create second type and DTO for that type
        Long id2 = 2L;
        String name2 = "Jméno 2";
        String description2 = "Popis 2";
        BigDecimal price2 = new BigDecimal("424");

        ServiceType type2 = new ServiceType(name2, description2, price2);

        // set id fields for both types
        Field field = type2.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(type1, id1);
        field.set(type2, id2);

        ServiceTypeDTO dto2 = new ServiceTypeDTO();
        dto2.setId(id2);
        dto2.setName(name2);
        dto2.setDescription(description2);
        dto2.setPricePerHour(price2);

        List<ServiceType> types = new ArrayList<>();
        types.add(type1);
        types.add(type2);

        List<ServiceTypeDTO> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);

        when(serviceMock.findAll()).thenReturn(types);

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock, beanMappingService);
        List<ServiceTypeDTO> returnedDtos = facade.getAllServiceTypes();

        verify(serviceMock).findAll();
        Assert.assertTrue(returnedDtos.size() == 2);
        Assert.assertEquals(dtos, returnedDtos);
    }
}
