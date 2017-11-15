package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.ServiceTypeDao;
import cz.muni.fi.pa165.entity.ServiceType;
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
import java.util.List;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestServiceTypeDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void storesAndReceivesSingleService() {
        ServiceType serviceType = createSingleServiceType();

        List<ServiceType> types = serviceTypeDao.findAll();
        Assert.assertEquals(types.size(), 1);

        ServiceType typeFromDao = types.get(0);
        Assert.assertTrue(serviceType.equals(typeFromDao));
    }

    private ServiceType createSingleServiceType() {
        ServiceType serviceType = new ServiceType();
        serviceType.setName("Trimming");
        serviceType.setDescription("Basic trimming service for all different kinds of dogs");
        serviceType.setPricePerHour(new BigDecimal("150"));
        serviceTypeDao.create(serviceType);
        return serviceType;
    }

    @Test
    public void findsTypeById() {
        ServiceType type = createSingleServiceType();

        ServiceType foundType = serviceTypeDao.findById(type.getId());
        Assert.assertTrue(type.equals(foundType));
    }

    @Test
    public void updatesDescription() {
        String newDescription = "Awesome basic trimming service";

        ServiceType type = createSingleServiceType();
        em.detach(type);
        type.setDescription(newDescription);
        serviceTypeDao.update(type);
        List<ServiceType> types = serviceTypeDao.findAll();
        Assert.assertTrue(types.get(0).getDescription().equals(newDescription));
    }

    @Test
    public void deletesSingleType() {
        ServiceType type = createSingleServiceType();
        List<ServiceType> types = serviceTypeDao.findAll();

        Assert.assertEquals(types.size(), 1);

        serviceTypeDao.delete(type);

        List<ServiceType> typesAfterDeletion = serviceTypeDao.findAll();
        Assert.assertEquals(typesAfterDeletion.size(), 0);
    }
}
