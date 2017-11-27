package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.HashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Kuchar 433499
 */

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestCustomerService extends AbstractTransactionalTestNGSpringContextTests{

    @Mock
    private CustomerDao customerDao;

    @Mock
    private DogDao dogDao;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Customer getOneCustomer(){
        Customer customer = new Customer();
        customer.setName("Name");
        customer.setSurname("Surname");
        customer.setPhoneNumber("123");
        customer.setAddress(new Address("City", "Street", 47));
        customer.setDogs(new HashSet<>());

        return customer;
    }

    private Dog getOneDog(){
        return new Dog("Rex", "German Shephard", new Date(2016,1,1), Gender.MALE, getOneCustomer());
    }

    @Test
    public void foundCustomerWithExactId(){
        Long idToBeFound = 47L;

        customerService.findById(idToBeFound);

        verify(customerDao).findById(idToBeFound);
    }

    @Test
    public void foundAllCustomers(){
        customerService.findAll();

        verify(customerDao).findAll();
    }

    @Test
    public void createCustomerCorrectly(){
        Customer customer = getOneCustomer();

        customerService.create(customer);

        verify(customerDao).create(customer);
    }

    @Test
    public void removedExistingCustomer(){
        Customer customer = getOneCustomer();

        customerService.delete(customer);

        verify(customerDao).delete(customer);
    }

    @Test
    public void retrievedAllCustomersMatchingSurname(){
        String surname = "Our Special Surname";

        customerService.getAllMatchingSurname(surname);

        verify(customerDao).getAllMatchingSurname(surname);
    }

    @Test
    public void retrievedAllCustomersMatchingPhoneNumbers(){
        String phoneNumber = "123456789";

        customerService.getAllMatchingPhoneNumber(phoneNumber);

        verify(customerDao).getAllMatchingPhoneNumber(phoneNumber);
    }

    @Test
    public void updatedCustomer(){
        Customer customer = getOneCustomer();

        customerService.update(customer);

        verify(customerDao).update(customer);
    }

    @Test
    public void getOwnerOfDog(){
        Dog dog = getOneDog();

        when(dogDao.findById(dog.getId())).thenReturn(dog);
        when(customerDao.findById(dog.getOwner().getId())).thenReturn(dog.getOwner());

        Customer owner = customerService.getOwnerOfDog(dog.getId());

        Assert.assertEquals(dog.getOwner(), owner);
    }
}
