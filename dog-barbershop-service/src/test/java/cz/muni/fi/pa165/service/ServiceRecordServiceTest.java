package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class ServiceRecordServiceTest extends AbstractTransactionalTestNGSpringContextTests {

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

    @Test
    public void getsTurnoverForLastMonth() {
        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfPreviousMonth = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDayOfPreviousMonth = calendar.getTime();

        ServiceRecord record1 = new ServiceRecord();
        BigDecimal price1 = new BigDecimal("15.38");
        record1.setActualPrice(price1);

        ServiceRecord record2 = new ServiceRecord();
        BigDecimal price2 = new BigDecimal("158.98");
        record2.setActualPrice(price2);

        List<ServiceRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        BigDecimal expectedTurnover = price1.add(price2);

        when(timeServiceMock.getCurrentTime()).thenReturn(currentTime);
        when(daoMock.getServicesProvidedBetween(firstDayOfPreviousMonth, lastDayOfPreviousMonth)).thenReturn(records);
        BigDecimal turnoverReturned = service.getTurnoverForLastMonth();

        verify(daoMock).getServicesProvidedBetween(firstDayOfPreviousMonth, lastDayOfPreviousMonth);
        Assert.assertEquals(expectedTurnover, turnoverReturned);
    }
}
