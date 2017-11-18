package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.ServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestServiceRecordDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceRecordDao serviceRecordDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void storesAndReceivesSingleService() {
        ServiceRecord serviceRecord = createSingleService();

        List<ServiceRecord> serviceRecords = serviceRecordDao.findAll();
        Assert.assertEquals(serviceRecords.size(), 1);

        ServiceRecord serviceRecordFromDao = serviceRecords.get(0);
        Assert.assertTrue(serviceRecord.equals(serviceRecordFromDao));
    }

    @Test
    public void storesAndDeletesSingleService() {
        ServiceRecord serviceRecord = createSingleService();

        List<ServiceRecord> serviceRecords = serviceRecordDao.findAll();
        Assert.assertEquals(serviceRecords.size(), 1);

        serviceRecordDao.delete(serviceRecord);

        List<ServiceRecord> services2 = serviceRecordDao.findAll();
        Assert.assertEquals(services2.size(), 0);
    }

    private ServiceRecord createSingleService() {
        ServiceRecord serviceRecord = new ServiceRecord();
        serviceRecord.setLength(Time.valueOf("00:10:00"));
        serviceRecord.setDog(null);
        serviceRecord.setEmployee(null);
        serviceRecord.setDateProvided(Date.valueOf("2017-10-28"));
        serviceRecord.setActualPrice(BigDecimal.TEN);
        serviceRecordDao.create(serviceRecord);
        return serviceRecord;
    }

    @Test
    public void findsServiceById() {
        ServiceRecord serviceRecord = createSingleService();

        ServiceRecord foundServiceRecord = serviceRecordDao.findById(serviceRecord.getId());
        Assert.assertTrue(serviceRecord.equals(foundServiceRecord));
    }

    @Test
    public void doesntFindNonExistentService() {
        createSingleService();

        ServiceRecord foundServiceRecord = serviceRecordDao.findById(-1L);
        Assert.assertTrue(foundServiceRecord == null);
    }

    private List<ServiceRecord> createTestServices() {
        ServiceRecord simpleServiceRecord = new ServiceRecord();
        simpleServiceRecord.setLength(Time.valueOf("00:10:00"));
        simpleServiceRecord.setDateProvided(Date.valueOf("2017-10-28"));
        simpleServiceRecord.setActualPrice(BigDecimal.TEN);
        serviceRecordDao.create(simpleServiceRecord);

        ServiceRecord simplerServiceRecord = new ServiceRecord();
        simplerServiceRecord.setLength(Time.valueOf("00:05:00"));
        simplerServiceRecord.setDateProvided(Date.valueOf("2017-10-29"));
        simplerServiceRecord.setActualPrice(BigDecimal.ONE);
        serviceRecordDao.create(simplerServiceRecord);

        ServiceRecord difficultServiceRecord = new ServiceRecord();
        difficultServiceRecord.setLength(Time.valueOf("00:45:00"));
        difficultServiceRecord.setDateProvided(Date.valueOf("2017-10-15"));
        difficultServiceRecord.setActualPrice(BigDecimal.TEN);

        serviceRecordDao.create(difficultServiceRecord);

        return Arrays.asList(simpleServiceRecord, simplerServiceRecord, difficultServiceRecord);
    }

    @Test
    public void findsAllServicesProvidedBetweenTwoDates() {
        createTestServices();

        List<ServiceRecord> serviceRecords = serviceRecordDao.getServicesProvidedBetween(Date.valueOf("2017-10-20"), Date.valueOf("2017-10-30"));
        Assert.assertEquals(serviceRecords.size(), 2);
    }

    @Test
    public void updatesServiceActualPrice() {
        ServiceRecord serviceRecord = createSingleService();
        em.detach(serviceRecord);
        serviceRecord.setActualPrice(new BigDecimal("20"));
        serviceRecordDao.update(serviceRecord);
        List<ServiceRecord> foundServiceRecord = serviceRecordDao.findAll();
        Assert.assertTrue(foundServiceRecord.get(0).getActualPrice().equals(new BigDecimal("20")));
    }
}
