package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {MappingServiceConfiguration.class, PersistenceSampleApplicationContext.class})
public class TestCustomerService {

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void initializeData(){

    }

    @Test
    public void foundCustomerWithExactId(){
        //
    }

    @Test
    public void foundAllCustomers(){
        //
    }

    @Test
    public void createCustomerCorrectly(){
        //
    }

    @Test
    public void didntCreateCustomerThatAlreadyExists(){
        //
    }

    @Test
    public void removedExistingCustomer(){
        //
    }

    @Test
    public void triedToRemoveNonExistingCustomer(){
        //
    }

    @Test
    public void retrievedAllCustomersMatchingSurname(){
        //
    }

    @Test
    public void retrievedAllCustomersMatchingPhoneNumbers(){
        //
    }
}
