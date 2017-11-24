package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CustomerService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestCustomerFacade extends AbstractTransactionalTestNGSpringContextTests{

    @Mock
    private CustomerService customerService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private CustomerFacade customerFacade = new CustomerFacadeImpl();

    @Test
    public void allCustomersRetrieved(){
        //
    }

    @Test
    public void foundExistingCustomerWithGivenId(){
        //
    }

    @Test
    public void retrieveAllCustomersWithMatchingSurnames(){
        //
    }

    @Test
    public void retrieveAllCustomersWithMatchingPhoneNumbers(){
        //
    }

    @Test
    public void createNonExistingCustomer(){
        //
    }

    @Test
    public void deleteExistingCustomer(){
        //
    }

    @Test
    public void updatedExistingCustomer(){
        //
    }
}
