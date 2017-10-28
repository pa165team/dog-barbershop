package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.transaction.Transactional;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestCustomerDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerDao customerDao;

    @Test
    @Rollback()
    public void storeAndReceiveSingleCustomer() {
        Customer customer = new Customer();
        customer.setName("Jan");
        customer.setSurname("Novák");
        customer.setPhoneNumber("123456789");
        customer.setAddress(new Address("Brno", "Cejl", 1234));

        customerDao.create(customer);
        List<Customer> customers = customerDao.findAll();

        Assert.assertEquals(customers.size(), 1);

        Customer customerFromDao = customers.get(0);
        Assert.assertEquals(customer.getName(), customerFromDao.getName());
    }
}
