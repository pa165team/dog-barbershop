package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.ServiceDao;
import cz.muni.fi.pa165.entity.Service;
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
public class TestServiceDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceDao serviceDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void storesAndReceivesSingleService() {
        Service service = createSingleService();

        List<Service> services = serviceDao.findAll();
        Assert.assertEquals(services.size(), 1);

        Service serviceFromDao = services.get(0);
        Assert.assertTrue(service.equals(serviceFromDao));
    }

    @Test
    public void storesAndDeletesSingleService() {
        Service service = createSingleService();

        List<Service> services = serviceDao.findAll();
        Assert.assertEquals(services.size(), 1);

        serviceDao.delete(service);

        List<Service> services2 = serviceDao.findAll();
        Assert.assertEquals(services2.size(), 0);
    }

    private Service createSingleService() {
        Service service = new Service();
        service.setLength(Time.valueOf("00:10:00"));
        service.setDog(null);
        service.setEmployee(null);
        service.setDateProvided(Date.valueOf("2017-10-28"));
        service.setActualPrice(BigDecimal.TEN);
        serviceDao.create(service);
        return service;
    }

    @Test
    public void findsServiceById() {
        Service service = createSingleService();

        Service foundService = serviceDao.findById(service.getId());
        Assert.assertTrue(service.equals(foundService));
    }

    @Test
    public void doesntFindNonExistentService() {
        createSingleService();

        Service foundService = serviceDao.findById(-1L);
        Assert.assertTrue(foundService == null);
    }

    private List<Service> createTestServices() {
        Service simpleService = new Service();
        simpleService.setLength(Time.valueOf("00:10:00"));
        simpleService.setDateProvided(Date.valueOf("2017-10-28"));
        simpleService.setActualPrice(BigDecimal.TEN);
        serviceDao.create(simpleService);

        Service simplerService = new Service();
        simplerService.setLength(Time.valueOf("00:05:00"));
        simplerService.setDateProvided(Date.valueOf("2017-10-29"));
        simplerService.setActualPrice(BigDecimal.ONE);
        serviceDao.create(simplerService);

        Service difficultService = new Service();
        difficultService.setLength(Time.valueOf("00:45:00"));
        difficultService.setDateProvided(Date.valueOf("2017-10-15"));
        difficultService.setActualPrice(BigDecimal.TEN);

        serviceDao.create(difficultService);

        return Arrays.asList(simpleService, simplerService, difficultService);
    }

    @Test
    public void findsAllServicesProvidedBetweenTwoDates() {
        createTestServices();

        List<Service> services = serviceDao.getServicesProvidedBetween(Date.valueOf("2017-10-20"), Date.valueOf("2017-10-30"));
        Assert.assertEquals(services.size(), 2);
    }

    @Test
    public void updatesServiceActualPrice() {
        Service service = createSingleService();
        em.detach(service);
        service.setActualPrice(new BigDecimal("20"));
        serviceDao.update(service);
        List<Service> foundService = serviceDao.findAll();
        Assert.assertTrue(foundService.get(0).getActualPrice().equals(new BigDecimal("20")));
    }
}
