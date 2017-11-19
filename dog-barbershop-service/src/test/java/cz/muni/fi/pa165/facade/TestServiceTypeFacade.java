package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.DogBarbershopServiceException;
import cz.muni.fi.pa165.service.ServiceTypeService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestServiceTypeFacade extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceTypeService serviceMock;
    private ServiceType typeMock;
    private static final Long ID = 1L;

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

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock);
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

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock);
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

        ServiceTypeFacadeImpl facade = new ServiceTypeFacadeImpl(serviceMock);
        when(serviceMock.findById(ID)).thenReturn(typeMock);

        try {
            facade.changePricePerHour(dto);
            Assert.fail("It should throw DogBarbershopServiceException");
        } catch (DogBarbershopServiceException ex) {

        }
        verifyZeroInteractions(typeMock);
    }

    // TODO: otestovat createServiceType, getAllServiceTypes
}
