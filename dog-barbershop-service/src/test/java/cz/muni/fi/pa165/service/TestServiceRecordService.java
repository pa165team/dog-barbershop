package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestServiceRecordService extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceRecordService service;
    private ServiceRecordDao daoMock;
    private TimeService timeServiceMock;

    @BeforeMethod
    public void setupEvery() {
        daoMock = mock(ServiceRecordDao.class);
        timeServiceMock = mock(TimeService.class);
        service = new ServiceRecordServiceImpl(daoMock, timeServiceMock);
    }

    @Test
    public void createsServiceRecord() {
        ServiceRecord record = new ServiceRecord();

        service.create(record);

        verify(daoMock).create(record);
    }

    @Test
    public void getsServiceRecordsByDog() {
        Dog dog = new Dog();

        service.getServiceRecordsByDog(dog);

        verify(daoMock).getRecordsByDog(dog);
    }

    @Test
    public void getsServiceRecordsFromLastWeek() {
        Date currentTime = new Date();
        when(timeServiceMock.getCurrentTime()).thenReturn(currentTime);

        service.getServiceRecordsFromLastWeek();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        verify(daoMock).getServicesProvidedBetween(calendar.getTime(), currentTime);
    }
}
