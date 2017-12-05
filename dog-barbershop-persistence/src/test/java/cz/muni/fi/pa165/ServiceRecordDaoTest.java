package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ServiceRecordDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceRecordDao serviceRecordDao;

    @Autowired
    private DogDao dogDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    @Autowired
    private CustomerDao customerDao;

    @PersistenceContext
    private EntityManager em;

    private Customer sampleOwner;
    private Dog sampleDog;
    private Employee sampleEmployee;
    private ServiceType sampleServiceType;

    @BeforeMethod
    private void beforeEvery() {
        sampleOwner = createSampleOwner();
        sampleDog = createSampleDog();
        sampleEmployee = createSampleEmployee();
        sampleServiceType = createSampleServiceType();
    }

    private Dog createSampleDog() {
        Dog dog = new Dog();
        dog.setName("Jméno");
        dog.setBreed("Plemeno");
        dog.setDateOfBirth(Date.valueOf("2007-05-10"));
        dog.setGender(Gender.FEMALE);
        dog.setOwner(sampleOwner);
        dogDao.create(dog);
        return dog;
    }

    private Dog createSampleDog2() {
        Dog dog = new Dog();
        dog.setName("Jiné jméno");
        dog.setBreed("Jiné plemeno");
        dog.setDateOfBirth(Date.valueOf("2009-05-10"));
        dog.setGender(Gender.MALE);
        dog.setOwner(sampleOwner);
        dogDao.create(dog);
        return dog;
    }

    private Customer createSampleOwner() {
        Customer owner = new Customer();
        owner.setName("Jan");
        owner.setSurname("Novák");
        owner.setAddress(new Address("Brno", "Náměstí svobody", 20));
        owner.setPhoneNumber("123456789");
        customerDao.create(owner);
        return owner;
    }

    private Employee createSampleEmployee() {
        Employee employee = new Employee();
        employee.setName("Josef");
        employee.setSurname("Pepa");
        employee.setAddress(new Address("Brno", "Konečného náměstí", 1234));
        employee.setSalary(new BigDecimal("20000"));
        employee.setPhoneNumber("123456789");
        employeeDao.create(employee);
        return employee;
    }

    private ServiceType createSampleServiceType() {
        ServiceType serviceType = new ServiceType();
        serviceType.setName("Stříhání");
        serviceType.setDescription("Jednoduché stříhání");
        serviceType.setPricePerHour(new BigDecimal("200"));
        serviceTypeDao.create(serviceType);
        return serviceType;
    }

    @Test
    public void storesAndReceivesSingleRecord() {
        ServiceRecord serviceRecord = createSingleRecord();

        List<ServiceRecord> serviceRecords = serviceRecordDao.findAll();
        Assert.assertEquals(serviceRecords.size(), 1);

        ServiceRecord serviceRecordFromDao = serviceRecords.get(0);
        Assert.assertTrue(serviceRecord.equals(serviceRecordFromDao));
    }

    @Test
    public void storesAndDeletesSingleRecord() {
        ServiceRecord serviceRecord = createSingleRecord();

        List<ServiceRecord> serviceRecords = serviceRecordDao.findAll();
        Assert.assertEquals(serviceRecords.size(), 1);

        serviceRecordDao.delete(serviceRecord);

        List<ServiceRecord> services2 = serviceRecordDao.findAll();
        Assert.assertEquals(services2.size(), 0);
    }

    private ServiceRecord createSingleRecord() {
        return createSingleRecord(Date.valueOf("2017-10-28"), 10);
    }

    private ServiceRecord createSingleRecord(Date date, Integer length) {
        ServiceRecord serviceRecord = new ServiceRecord();
        serviceRecord.setActualPrice(BigDecimal.TEN);
        serviceRecord.setDateProvided(date);
        serviceRecord.setLengthMinutes(length);
        serviceRecord.setDog(sampleDog);
        serviceRecord.setEmployee(sampleEmployee);
        serviceRecord.setServiceType(sampleServiceType);
        serviceRecordDao.create(serviceRecord);
        return serviceRecord;
    }

    @Test
    public void findsServiceById() {
        ServiceRecord serviceRecord = createSingleRecord();

        ServiceRecord foundServiceRecord = serviceRecordDao.findById(serviceRecord.getId());
        Assert.assertTrue(serviceRecord.equals(foundServiceRecord));
    }

    @Test
    public void doesntFindNonExistentService() {
        createSingleRecord();

        ServiceRecord foundServiceRecord = serviceRecordDao.findById(-1L);
        Assert.assertTrue(foundServiceRecord == null);
    }

    private List<ServiceRecord> createTestRecords() {
        ServiceRecord one = createSingleRecord(Date.valueOf("2017-10-28"), 10);
        ServiceRecord two = createSingleRecord(Date.valueOf("2017-10-29"), 5);
        ServiceRecord three = createSingleRecord(Date.valueOf("2017-10-15"), 45);
        return Arrays.asList(one, two, three);
    }

    @Test
    public void findsAllServicesProvidedBetweenTwoDates() {
        createTestRecords();

        List<ServiceRecord> serviceRecords = serviceRecordDao.getServicesProvidedBetween(Date.valueOf("2017-10-20"), Date.valueOf("2017-10-30"));
        Assert.assertEquals(serviceRecords.size(), 2);
    }

    @Test
    public void updatesServiceActualPrice() {
        ServiceRecord serviceRecord = createSingleRecord();
        em.detach(serviceRecord);
        serviceRecord.setActualPrice(new BigDecimal("20"));
        serviceRecordDao.update(serviceRecord);
        List<ServiceRecord> foundServiceRecord = serviceRecordDao.findAll();
        Assert.assertTrue(foundServiceRecord.get(0).getActualPrice().equals(new BigDecimal("20")));
    }

    @Test
    public void getRecordsByDog() {
        ServiceRecord serviceRecord1 = new ServiceRecord();
        serviceRecord1.setActualPrice(new BigDecimal(400));
        serviceRecord1.setDateProvided(Date.valueOf("2017-11-18"));
        serviceRecord1.setLengthMinutes(80);
        serviceRecord1.setDog(sampleDog);
        serviceRecord1.setEmployee(sampleEmployee);
        serviceRecord1.setServiceType(sampleServiceType);

        Dog dog2 = createSampleDog2();
        ServiceRecord serviceRecord2 = new ServiceRecord();
        serviceRecord2.setActualPrice(new BigDecimal(200));
        serviceRecord2.setDateProvided(Date.valueOf("2017-01-01"));
        serviceRecord2.setLengthMinutes(30);
        serviceRecord2.setDog(dog2);
        serviceRecord2.setEmployee(sampleEmployee);
        serviceRecord2.setServiceType(sampleServiceType);

        serviceRecordDao.create(serviceRecord1);
        serviceRecordDao.create(serviceRecord2);

        List<ServiceRecord> recordsByDog = serviceRecordDao.getRecordsByDog(sampleDog);
        Assert.assertEquals(1, recordsByDog.size());
        Assert.assertEquals(sampleDog, recordsByDog.get(0).getDog());

        List<ServiceRecord> recordsByDog2 = serviceRecordDao.getRecordsByDog(dog2);
        Assert.assertEquals(1, recordsByDog2.size());
        Assert.assertEquals(dog2, recordsByDog2.get(0).getDog());
    }
}
