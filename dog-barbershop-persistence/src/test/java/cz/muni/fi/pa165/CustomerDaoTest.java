package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jan Kalfus
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerDao customerDao;

    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void storesAndReceivesSingleCustomer() {
        Customer customer = createSingleCustomer();

        List<Customer> customers = customerDao.findAll();
        Assert.assertEquals(customers.size(), 1);

        Customer customerFromDao = customers.get(0);
        Assert.assertTrue(customer.equals(customerFromDao));
    }

    private Customer createSingleCustomer() {
        Customer customer = new Customer();
        customer.setName("Jan");
        customer.setSurname("Novák");
        customer.setPhoneNumber("123456789");
        customer.setAddress(new Address("Brno", "Cejl", 1234));

        customerDao.create(customer);
        return customer;
    }

    @Test
    public void storesAndDeletesSingleCustomer() {
        Customer customer = createSingleCustomer();

        List<Customer> customers = customerDao.findAll();
        Assert.assertEquals(customers.size(), 1);

        customerDao.delete(customer);

        List<Customer> customers2 = customerDao.findAll();
        Assert.assertEquals(customers2.size(), 0);
    }

    @Test
    public void findsCustomerById() {
        Customer customer = createSingleCustomer();

        Customer foundCustomer = customerDao.findById(customer.getId());
        Assert.assertTrue(customer.equals(foundCustomer));
    }

    @Test
    public void doesntFindNonExistentCustomer() {
        createSingleCustomer();

        Customer foundCustomer = customerDao.findById(-1L);
        Assert.assertTrue(foundCustomer == null);
    }

    @Test
    public void findsAllCustomersWithCertainSurname() {
        addTwoNovaksAndOneOther();

        List<Customer> novaks = customerDao.getAllMatchingSurname("Novák");
        Assert.assertTrue(novaks.size() == 2);
    }

    private List<Customer> addTwoNovaksAndOneOther() {
        Customer janNovak = new Customer();
        janNovak.setName("Jan");
        janNovak.setSurname("Novák");
        janNovak.setPhoneNumber("789456123");
        janNovak.setAddress(new Address("Brno", "Česká", 123));

        Customer janJiny = new Customer();
        janJiny.setName("Jan");
        janJiny.setSurname("Jiný");
        janJiny.setPhoneNumber("123456789");
        janJiny.setAddress(new Address("Praha", "Václavské náměstí", 456));

        Customer pepaNovak = new Customer();
        pepaNovak.setName("Josef");
        pepaNovak.setSurname("Novák");
        pepaNovak.setPhoneNumber("456789123");
        pepaNovak.setAddress(new Address("Ostrava", "Výstavní", 1));

        customerDao.create(janNovak);
        customerDao.create(janJiny);
        customerDao.create(pepaNovak);

        return Arrays.asList(janNovak, janJiny, pepaNovak);
    }

    @Test
    public void gettingAllCustomersWithSurnameWhichIsNotStoredReturnsNull() {
        addTwoNovaksAndOneOther();

        List<Customer> customers = customerDao.getAllMatchingSurname("Random");
        Assert.assertEquals(customers.size(), 0);
    }

    @Test
    public void findsAllCustomersWithCertainPhoneNumber() {
        final String phoneNumberContaining = "4567";

        List<Customer> addedCustomers = addTwoNovaksAndOneOther();
        List<Customer> customersWithMatchingPhoneNumbers = addedCustomers.stream()
            .filter(c -> c.getPhoneNumber().contains(phoneNumberContaining))
            .collect(Collectors.toList());

        List<Customer> customers = customerDao.getAllMatchingPhoneNumber(phoneNumberContaining);
        Assert.assertEquals(customers.size(), 2);
        Assert.assertTrue(customers.contains(customersWithMatchingPhoneNumbers.get(0)));
        Assert.assertTrue(customers.contains(customersWithMatchingPhoneNumbers.get(1)));
    }
    
    @Test
    public void updateCustomerSurname() {
        Customer customer = createSingleCustomer();
        em.detach(customer);
        customer.setSurname("Nemozny");
        customerDao.update(customer);
        List<Customer> novaks = customerDao.getAllMatchingSurname("Nemozny");
        Assert.assertTrue(novaks.size() == 1);
    }
}
