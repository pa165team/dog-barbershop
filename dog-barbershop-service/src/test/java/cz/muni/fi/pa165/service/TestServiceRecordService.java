package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestServiceRecordService extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceRecordService service;
    private ServiceRecordDao daoMock;

    @BeforeMethod
    public void setupEvery() {
        daoMock = mock(ServiceRecordDao.class);
        service = new ServiceRecordServiceImpl(daoMock);
    }

    @Test
    public void createServiceRecord() {
        ServiceRecord record = new ServiceRecord();

        service.create(record);

        verify(daoMock).create(record);
    }

    @Test
    public void getServiceRecordsByDog() {
        Dog dog = new Dog();

        service.getServiceRecordsByDog(dog);

        verify(daoMock).getRecordsByDog(dog);
    }
}
