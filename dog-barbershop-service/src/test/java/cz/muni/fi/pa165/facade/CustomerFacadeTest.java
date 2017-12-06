package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Martin Kuchar 433499
 */

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class CustomerFacadeTest extends AbstractTransactionalTestNGSpringContextTests{

    @Mock
    private CustomerService customerServiceMock;

    @Mock
    private Customer customerMock;

    @Mock
    private BeanMappingService beanMappingServiceMock;

    private CustomerFacade customerFacadeWithMocks;

    //=============================================

    @Autowired
    private DogDao dogDao;

    @Autowired
    private CustomerDao customerDao;

    private CustomerService customerService;

    private CustomerFacade customerFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    //==============================================

    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.customerFacadeWithMocks = new CustomerFacadeImpl(customerServiceMock, beanMappingServiceMock);
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

    private List<Customer> initializeCustomers() {
        Customer c1 = new Customer();
        c1.setName("Name1");
        c1.setSurname("Surname1");
        c1.setAddress(new Address("City", "Street", 47));
        c1.setPhoneNumber("123456789");
        c1.setDogs(new HashSet<>());

        Customer c2 = new Customer();
        c2.setName("Name2");
        c2.setSurname("Surname2");
        c2.setAddress(new Address("Town", "Teerts", 74));
        c2.setPhoneNumber("987654321");
        c2.setDogs(new HashSet<>());

        Customer c3 = new Customer();
        c3.setName("Name3");
        c3.setSurname("Surname3");
        c3.setAddress(new Address("Nice City", "Ugly Street", 42));
        c3.setPhoneNumber("123987456");
        c3.setDogs(new HashSet<>());

        customerService = new CustomerServiceImpl(customerDao, dogDao);
        customerService.create(c1);
        customerService.create(c2);
        customerService.create(c3);

        customerFacade = new CustomerFacadeImpl(customerService, beanMappingService);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(c1);
        customerList.add(c2);
        customerList.add(c3);

        return customerList;
    }

    private List<CustomerDTO> getListCustomerDTOFromListCustomers(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(customerDTO);

        return customerDTOS;
    }

    private CustomerDTO getCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setId(customer.getId());
        customerDTO.setServiceRecords(new ArrayList<>());
        return customerDTO;
    }

    @Test
    public void retrieveAllCustomersWithMatchingSurnames(){
        List<Customer> customers = customers();

        List<Customer> returnListForFirstSurname = new ArrayList<>();
        returnListForFirstSurname.add(customers.get(0));

        List<CustomerDTO> customerDTOS = getListCustomerDTOFromListCustomers(returnListForFirstSurname.get(0));

        when(customerServiceMock.getAllMatchingSurname("Surname1")).thenReturn(returnListForFirstSurname);
        when(beanMappingServiceMock.mapTo(returnListForFirstSurname, CustomerDTO.class)).thenReturn(customerDTOS);

        List<CustomerDTO> customersWithSurname = customerFacadeWithMocks.getAllCustomersMatchingSurname("Surname1");

        Assert.assertEquals(1, customersWithSurname.size());
        Assert.assertEquals("Name1", customersWithSurname.get(0).getName());
        Assert.assertEquals("Surname1", customersWithSurname.get(0).getSurname());
        Assert.assertEquals(customers.get(0).getAddress(), customersWithSurname.get(0).getAddress());
        Assert.assertEquals("123456789", customersWithSurname.get(0).getPhoneNumber());
    }

    @Test
    public void retrieveAllCustomersWithMatchingPhoneNumbers(){
        List<Customer> customers = customers();

        List<Customer> returnListForSecondPhoneNumber = new ArrayList<>();
        returnListForSecondPhoneNumber.add(customers.get(1));

        List<CustomerDTO> customerDTOS = getListCustomerDTOFromListCustomers(returnListForSecondPhoneNumber.get(0));

        when(customerServiceMock.getAllMatchingPhoneNumber("987654321")).thenReturn(returnListForSecondPhoneNumber);
        when(beanMappingServiceMock.mapTo(returnListForSecondPhoneNumber, CustomerDTO.class)).thenReturn(customerDTOS);

        List<CustomerDTO> customersWithPhoneNumber = customerFacadeWithMocks.getAllCustomersMatchingPhoneNumber("987654321");

        Assert.assertEquals(1, customersWithPhoneNumber.size());
        Assert.assertEquals("Name2", customersWithPhoneNumber.get(0).getName());
        Assert.assertEquals("Surname2", customersWithPhoneNumber.get(0).getSurname());
        Assert.assertEquals(customers.get(1).getAddress(), customersWithPhoneNumber.get(0).getAddress());
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

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        when(customerServiceMock.create(captor.capture())).thenReturn(customerMock);
        when(customerMock.getId()).thenReturn(id);

        customerFacadeWithMocks.createCustomer(dto);

        Customer captured = captor.getValue();

        Assert.assertEquals(name, captured.getName());
        Assert.assertEquals(surname, captured.getSurname());
        Assert.assertEquals(address, captured.getAddress());
        Assert.assertEquals(phoneNumber, captured.getPhoneNumber());
    }

    @Test
    public void getCustomerById(){
        List<Customer> customers = initializeCustomers();

        Assert.assertEquals(
            customers.get(0).getName(),
            customerFacade.getCustomerById(customers.get(0).getId()).getName()
        );
        Assert.assertEquals(
            customers.get(0).getSurname(),
            customerFacade.getCustomerById(customers.get(0).getId()).getSurname()
        );
        Assert.assertEquals(
            customers.get(0).getAddress(),
            customerFacade.getCustomerById(customers.get(0).getId()).getAddress()
        );
        Assert.assertEquals(
            customers.get(0).getPhoneNumber(),
            customerFacade.getCustomerById(customers.get(0).getId()).getPhoneNumber()
        );
    }

    @Test
    public void updateCustomer(){
        List<Customer> customers = initializeCustomers();

        CustomerDTO customerDTO = getCustomerDTO(customers.get(0));
        customerDTO.setPhoneNumber("192837465");
        customers.get(0).setPhoneNumber("192837465");

        when(beanMappingServiceMock.mapTo(customerDTO, Customer.class)).thenReturn(customers.get(0));

        Long customerIdReturned = customerFacadeWithMocks.updateCustomer(customerDTO);
        verify(customerServiceMock).update(customers.get(0));
        Assert.assertEquals(customers.get(0).getId(), customerIdReturned);
    }

    @Test
    public void getAllCustomers(){
        initializeCustomers();

        List<CustomerDTO> customers = customerFacade.getAllCustomers();
        Assert.assertTrue(customers.size() == 3);
    }

    @Test
    public void deleteCustomer(){
        List<Customer> customers = initializeCustomers();

        CustomerDTO customerDTO = getCustomerDTO(customers.get(0));

        when(beanMappingServiceMock.mapTo(customerDTO, Customer.class)).thenReturn(customers.get(0));

        customerFacadeWithMocks.deleteCustomer(customerDTO);

        verify(customerServiceMock).delete(customers.get(0));

    }
}
