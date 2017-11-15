package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestCustomerFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void initializeData(){
        //
    }

    @Test
    public void allCustomersRetrieved(){
        //
    }

    @Test
    public void foundExistingCustomerWithGivenId(){
        //
    }

    @Test
    public void didntFindCustomerWithWrongId(){
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
    public void didntCreateAlreadyExistingCustomer(){
        //
    }

    @Test
    public void deleteExistingCustomer(){
        //
    }

    @Test
    public void didntDeleteNonExistingCustomer(){
        //
    }

    @Test
    public void updatedExistingCustomer(){
        //
    }

    @Test
    public void didntUpdateNonExistingCustomer(){
        //
    }
}
