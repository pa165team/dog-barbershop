package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceTypeDao;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class ServiceTypeServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceTypeService serviceTypeService;
    private ServiceTypeDao daoMock;

    @BeforeMethod
    public void setupEvery() {
        daoMock = mock(ServiceTypeDao.class);
        serviceTypeService = new ServiceTypeServiceImpl(daoMock);
    }

    @Test
    public void createServiceType() {
        ServiceType serviceType = new ServiceType("Cutting", "Simple cutting", new BigDecimal("200"));

        serviceTypeService.create(serviceType);

        verify(daoMock).create(serviceType);
    }

    @Test
    public void findById() {
        Long id = 1L;

        ServiceType type = serviceTypeService.findById(id);

        verify(daoMock).findById(id);
    }

    @Test
    public void findAll() {
        serviceTypeService.findAll();
        verify(daoMock).findAll();
    }
}
