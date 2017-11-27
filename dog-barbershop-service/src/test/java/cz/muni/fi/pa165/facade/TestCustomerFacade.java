package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.CustomerDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CustomerService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Martin Kuchar 433499
 */

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestCustomerFacade extends AbstractTransactionalTestNGSpringContextTests{

    @Mock
    private CustomerService customerServiceMock;

    @Mock
    private Customer customerMock;

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    private List<Customer> customers(){
        Customer customer1 = new Customer();
        customer1.setName("Name1");
        customer1.setSurname("Surname1");
        customer1.setAddress(new Address("City1", "Street1", 47));
        customer1.setPhoneNumber("123456789");

        Customer customer2 = new Customer();
        customer2.setName("Name2");
        customer2.setSurname("Surname2");
        customer2.setAddress(new Address("City2", "Street2", 42));
        customer2.setPhoneNumber("987654321");

        List<Customer> array = new ArrayList<>();
        array.add(customer1);
        array.add(customer2);

        return array;
    }

    @Test
    public void retrieveAllCustomersWithMatchingSurnames(){
        CustomerFacade facade = new CustomerFacadeImpl(customerServiceMock, beanMappingService);

        List<Customer> customers = customers();

        List<Customer> returnListForFirstSurname = new ArrayList<>();
        returnListForFirstSurname.add(customers.get(0));

        when(customerServiceMock.getAllMatchingSurname("Surname1")).thenReturn(returnListForFirstSurname);

        List<CustomerDTO> customersWithSurname = facade.getAllCustomersMatchingSurname("Surname1");

        Assert.assertEquals(1, customersWithSurname.size());
        Assert.assertEquals("Name1", customersWithSurname.get(0).getName());
        Assert.assertEquals("Surname1", customersWithSurname.get(0).getSurname());
        Assert.assertEquals(customers.get(0).getAddress().getCity(), customersWithSurname.get(0).getAddress().getCity());
        Assert.assertEquals(customers.get(0).getAddress().getStreet(), customersWithSurname.get(0).getAddress().getStreet());
        Assert.assertEquals(customers.get(0).getAddress().getNumber(), customersWithSurname.get(0).getAddress().getNumber());
        Assert.assertEquals("123456789", customersWithSurname.get(0).getPhoneNumber());
    }

    @Test
    public void retrieveAllCustomersWithMatchingPhoneNumbers(){
        CustomerFacade facade = new CustomerFacadeImpl(customerServiceMock, beanMappingService);

        List<Customer> customers = customers();

        List<Customer> returnListForSecondPhoneNumber = new ArrayList<>();
        returnListForSecondPhoneNumber.add(customers.get(1));

        when(customerServiceMock.getAllMatchingPhoneNumber("987654321")).thenReturn(returnListForSecondPhoneNumber);

        List<CustomerDTO> customersWithPhoneNumber = facade.getAllCustomersMatchingPhoneNumber("987654321");

        Assert.assertEquals(1, customersWithPhoneNumber.size());
        Assert.assertEquals("Name2", customersWithPhoneNumber.get(0).getName());
        Assert.assertEquals("Surname2", customersWithPhoneNumber.get(0).getSurname());
        Assert.assertEquals(customers.get(1).getAddress().getCity(), customersWithPhoneNumber.get(0).getAddress().getCity());
        Assert.assertEquals(customers.get(1).getAddress().getStreet(), customersWithPhoneNumber.get(0).getAddress().getStreet());
        Assert.assertEquals(customers.get(1).getAddress().getNumber(), customersWithPhoneNumber.get(0).getAddress().getNumber());
        Assert.assertEquals("987654321", customersWithPhoneNumber.get(0).getPhoneNumber());
    }

    @Test
    public void createNonExistingCustomer(){
        final Long id = 47L;
        final String name = "Random";
        final String surname = "Name";
        final Address address = new Address("City", "Street", 47);
        final String phoneNumber = "123456789";

        CustomerCreateDTO dto = new CustomerCreateDTO(name, surname, address, phoneNumber);

        CustomerFacade facade = new CustomerFacadeImpl(customerServiceMock, beanMappingService);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        when(customerServiceMock.create(captor.capture())).thenReturn(customerMock);
        when(customerMock.getId()).thenReturn(id);

        facade.createCustomer(dto);

        Customer captured = captor.getValue();

        Assert.assertEquals(name, captured.getName());
        Assert.assertEquals(surname, captured.getSurname());
        Assert.assertEquals(address, captured.getAddress());
        Assert.assertEquals(phoneNumber, captured.getPhoneNumber());
    }

    //TODO: tests for all functions
}
