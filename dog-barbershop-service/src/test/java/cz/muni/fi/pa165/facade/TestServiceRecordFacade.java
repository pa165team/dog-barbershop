package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordCreateDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestServiceRecordFacade extends AbstractTransactionalTestNGSpringContextTests {

    private ServiceRecordService recordServiceMock;
    private ServiceTypeService typeServiceMock;
    private DogService dogServiceMock;
    private EmployeeService employeeServiceMock;
    private ServiceRecord recordMock;

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void setupEvery() {
        recordServiceMock = mock(ServiceRecordService.class);
        typeServiceMock = mock(ServiceTypeService.class);
        dogServiceMock = mock(DogService.class);
        employeeServiceMock = mock(EmployeeService.class);
        recordMock = mock(ServiceRecord.class);
    }

    @Test
    public void createsRecord() {
        final Long id = 42L;
        final Integer lengthMinutes = 10;

        ServiceRecordCreateDTO dto = new ServiceRecordCreateDTO();
        dto.setLengthMinutes(lengthMinutes);
        dto.setServiceTypeId(1L);
        dto.setDogId(1L);
        dto.setEmployeeId(1L);

        ServiceType type = new ServiceType();
        type.setPricePerHour(new BigDecimal("600"));
        type.setName("Test type");

        Dog dog = new Dog();
        Employee emp = new Employee();

        ServiceRecordFacade facade = new ServiceRecordFacadeImpl(recordServiceMock, typeServiceMock, dogServiceMock, employeeServiceMock, beanMappingService);
        when(typeServiceMock.findById(1L)).thenReturn(type);
        when(dogServiceMock.findById(1L)).thenReturn(dog);
        when(employeeServiceMock.findById(1L)).thenReturn(emp);

        ArgumentCaptor<ServiceRecord> captor = ArgumentCaptor.forClass(ServiceRecord.class);
        when(recordServiceMock.create(captor.capture())).thenReturn(recordMock);
        when(recordMock.getId()).thenReturn(id);

        Long newRecordId = facade.createServiceRecord(dto);

        ServiceRecord capturedValue = captor.getValue();
        Assert.assertEquals(lengthMinutes, capturedValue.getLengthMinutes());
        Assert.assertEquals(new BigDecimal("100"), capturedValue.getActualPrice());
        Assert.assertEquals(type, capturedValue.getServiceType());
        Assert.assertEquals(dog, capturedValue.getDog());
        Assert.assertEquals(emp, capturedValue.getEmployee());
        Assert.assertEquals(id, newRecordId);
    }

    @Test
    public void getsServiceRecordsByDog() {
        final Long id = 1L;
        ServiceRecordFacade facade = new ServiceRecordFacadeImpl(recordServiceMock, typeServiceMock, dogServiceMock, employeeServiceMock, beanMappingService);

        Dog dog = new Dog();
        BigDecimal price1 = new BigDecimal("100");
        BigDecimal price2 = new BigDecimal("420");

        ServiceRecord record1 = new ServiceRecord();
        record1.setActualPrice(price1);
        ServiceRecord record2 = new ServiceRecord();
        record2.setActualPrice(price2);

        ArrayList<ServiceRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);


        when(dogServiceMock.findById(id)).thenReturn(dog);
        when(recordServiceMock.getServiceRecordsByDog(dog)).thenReturn(records);

        List<ServiceRecordDTO> serviceRecordsByDog = facade.getServiceRecordsByDog(id);

        Assert.assertEquals(2, serviceRecordsByDog.size());
        Assert.assertEquals(price1, serviceRecordsByDog.get(0).getActualPrice());
        Assert.assertEquals(price2, serviceRecordsByDog.get(1).getActualPrice());
    }

    @Test
    public void getsServiceRecordsFromLastWeek() {
        ServiceRecordFacade facade = new ServiceRecordFacadeImpl(recordServiceMock, typeServiceMock, dogServiceMock, employeeServiceMock, beanMappingService);

        ArrayList<ServiceRecord> records = new ArrayList<>();
        ServiceRecord record1 = new ServiceRecord();
        Date dateProvided = new Date();
        record1.setDateProvided(dateProvided);
        records.add(record1);

        when(recordServiceMock.getServiceRecordsFromLastWeek()).thenReturn(records);

        List<ServiceRecordDTO> recordsReturned = facade.getServiceRecordsFromLastWeek();

        Assert.assertEquals(1, recordsReturned.size());
        Assert.assertEquals(dateProvided, recordsReturned.get(0).getDateProvided());
    }
}
