package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.dto.dog.DogCreateDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Lucie Kolarikova
 */

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestDogFacade extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private DogService dogServiceMock;
    @Mock
    private CustomerService customerServiceMock;
    @Mock
    private BeanMappingService beanMappingServiceMock;

    private CustomerService customerService;
    private DogService dogService;
    @Autowired
    private BeanMappingService beanMappingService;

    private DogFacadeImpl dogFacade;
    private DogFacadeImpl dogFacadeWithMocks;

    private Customer sampleOwner;
    private Dog dog1;

    @Autowired
    private DogDao dogDao;

    @Autowired
    private CustomerDao customerDao;

    // TestNG methods that are annotated with @BeforeMethod annotation will be run before executing each test method.
    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerDao, dogDao);

        sampleOwner = new Customer();
        sampleOwner.setName("Vlastnik");
        sampleOwner.setSurname("VsechPsu");
        sampleOwner.setAddress(new Address("BigCity", "Street", 55));
        sampleOwner.setPhoneNumber("123456789");
        customerService.create(sampleOwner);

        dogService = new DogServiceImpl(dogDao);

        dog1 = new Dog();
        dog1.setName("Alik");
        dog1.setBreed("Labrador");
        dog1.setDateOfBirth(Date.valueOf("2010-04-02"));
        dog1.setGender(Gender.MALE);
        dog1.setOwner(sampleOwner);
        dogService.create(dog1);

        Dog dog2 = new Dog();
        dog2.setName("Betty");
        dog2.setBreed("Poodle");
        dog2.setDateOfBirth(Date.valueOf("2007-12-08"));
        dog2.setGender(Gender.FEMALE);
        dog2.setOwner(sampleOwner);
        dogService.create(dog2);

        Dog dog3 = new Dog();
        dog3.setName("Azor");
        dog3.setBreed("Terrier");
        dog3.setDateOfBirth(Date.valueOf("2004-01-01"));
        dog3.setGender(Gender.MALE);
        dog3.setOwner(sampleOwner);
        dogService.create(dog3);

        Dog dog4 = new Dog();
        dog4.setName("Dusty");
        dog4.setBreed("Alaskan Malamute");
        dog4.setDateOfBirth(Date.valueOf("2009-07-30"));
        dog4.setGender(Gender.MALE);
        dog4.setOwner(sampleOwner);
        dogService.create(dog4);

        Dog dog5 = new Dog();
        dog5.setName("Poppo");
        dog5.setBreed("Papillon");
        dog5.setDateOfBirth(Date.valueOf("2011-02-17"));
        dog5.setGender(Gender.MALE);
        dog5.setOwner(sampleOwner);
        dogService.create(dog5);

        Dog dog6 = new Dog();
        dog6.setName("Lizzie");
        dog6.setBreed("Yorkshire Terrier");
        dog6.setDateOfBirth(Date.valueOf("2016-10-25"));
        dog6.setGender(Gender.FEMALE);
        dog6.setOwner(sampleOwner);
        dogService.create(dog6);

        dogFacade = new DogFacadeImpl(beanMappingService, dogService, customerService);
        dogFacadeWithMocks = new DogFacadeImpl(beanMappingServiceMock, dogServiceMock, customerServiceMock);
    }


    /**
     * Tests getAllDogs
     */
    @Test
    public void testGetAllDogs() {
        List<DogDTO> dogs = dogFacade.getAllDogs();
        Assert.assertTrue(dogs.size() == 6);
    }

    /**
     * Tests getDogById
     */
    @Test
    public void testGetDogById() {
        Assert.assertEquals(dog1.getDateOfBirth(), dogFacade.getDogById(dog1.getId()).getDateOfBirth());
        Assert.assertEquals(dog1.getBreed(), dogFacade.getDogById(dog1.getId()).getBreed());
        Assert.assertEquals(dog1.getName(), dogFacade.getDogById(dog1.getId()).getName());
    }

    /**
     * Tests getAllDogsOfGender
     */
    @Test
    public void testGetAllDogsOfGender() {
        Assert.assertEquals(2, dogFacade.getAllDogsOfGender(Gender.FEMALE).size());
        Assert.assertEquals(4, dogFacade.getAllDogsOfGender(Gender.MALE).size());
    }

    /**
     * Tests createDog
     */
    @Test
    public void testCreateDog() {
        DogCreateDTO dogCreateDTO = new DogCreateDTO("Vivian","SomeBreed", Date.valueOf("2017-11-08"), Gender.FEMALE, sampleOwner.getId());
        dogFacade.createDog(dogCreateDTO);

        Assert.assertEquals(7, dogFacade.getAllDogs().size());
    }


    @Test
    public void testRemoveDog() throws NoSuchFieldException, IllegalAccessException {
        final Long dogId = 8L;
        Customer customerMock = mock(Customer.class);

        DogDTO dogDTO = new DogDTO(dog1.getName(), dog1.getBreed(), dog1.getDateOfBirth(), dog1.getGender(), dog1.getHasDiscount(), dog1.getOwner().getId());
        dogDTO.setId(dogId);

        when(beanMappingServiceMock.mapTo(dogDTO, Dog.class)).thenReturn(dog1);
        when(customerServiceMock.getOwnerOfDog(dogId)).thenReturn(customerMock);

        dogFacadeWithMocks.removeDog(dogDTO);

        verify(customerMock).removeDog(dog1);
        verify(dogServiceMock).remove(dog1);
    }

    @Test
    public void testUpdateDog() {
        DogDTO dogDTO = new DogDTO(dog1.getName(), dog1.getBreed(), dog1.getDateOfBirth(), dog1.getGender(), dog1.getHasDiscount(), dog1.getOwner().getId());
        dogDTO.setBreed("Golden Retriever");
        Long dogId = 5L;
        dogDTO.setId(dogId);

        when(beanMappingServiceMock.mapTo(dogDTO, Dog.class)).thenReturn(dog1);

        Long dogIdReturned = dogFacadeWithMocks.updateDog(dogDTO);
        verify(dogServiceMock).update(dog1);
        Assert.assertEquals(dogId, dogIdReturned);
    }


    @Test
    public void testDrawLuckyDogToHaveDiscount() {
        List<DogDTO> allDogs = new ArrayList<>();
        allDogs.addAll(dogFacade.getAllDogs());

        for(DogDTO dog:allDogs) {
            Assert.assertFalse(dog.getHasDiscount()); // No dog has discount yet
        }

        DogDTO luckyDog = dogFacade.drawLuckyDogToHaveDiscount();
        Assert.assertTrue(luckyDog.getHasDiscount());

    }


}
