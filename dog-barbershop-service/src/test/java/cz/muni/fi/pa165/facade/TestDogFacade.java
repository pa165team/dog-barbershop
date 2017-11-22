package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.DogCreateDTO;
import cz.muni.fi.pa165.dto.DogDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.DogService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Lucie Kolarikova
 */

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestDogFacade extends AbstractTransactionalTestNGSpringContextTests {
//
//    private DogService dogServiceMock;
//    private Dog dogMock;
//    private static final Long ID = 7L;
//    private Customer sampleOwner;
//
//    @Autowired
//    private BeanMappingService beanMappingService;
//
//    // TestNG methods that are annotated with @BeforeMethod annotation will be run before executing each test method.
//    @BeforeMethod
//    public void setup() {
//        dogServiceMock = mock(DogService.class);
//        dogMock = mock(Dog.class);
//    }
//
//    @Test
//    public void testGetAllDogs() {
//
//        Dog dog1 = createDog("Alik", "Labrador", Date.valueOf("2010-04-02"), Gender.MALE);
//        DogDTO dogDTO1 = createDogDTO("Alik", "Labrador", Date.valueOf("2010-04-02"), Gender.MALE);
//
//        Dog dog2 = createDog("Betty", "Poodle", Date.valueOf("2007-12-08"), Gender.FEMALE);
//        DogDTO dogDTO2 = createDogDTO("Betty", "Poodle", Date.valueOf("2007-12-08"), Gender.FEMALE);
//
//        List<Dog> dogs = new ArrayList<>();
//        dogs.add(dog1);
//        dogs.add(dog2);
//
//        List<DogDTO> dogDTOs = new ArrayList<>();
//        dogDTOs.add(dogDTO1);
//        dogDTOs.add(dogDTO2);
//
//        when(dogServiceMock.findAll()).thenReturn(dogs);
//
//        DogFacadeImpl dogFacade = new DogFacadeImpl();
//        List<DogDTO> returnedDogDTOs = dogFacade.getAllDogs();
//
//        verify(dogServiceMock).findAll();
//
//        Assert.assertTrue(returnedDogDTOs.size()==2);
//        Assert.assertEquals(dogDTOs, returnedDogDTOs);
//
//    }
//
//    /**
//     * Creates a dog
//     * @param name Dog's name
//     * @param breed Dog's breed
//     * @param birth Date of birth
//     * @param gender Dog's gender
//     * @return Dog
//     */
//    private Dog createDog(String name, String breed, Date birth, Gender gender) {
//        Dog dog = new Dog();
//        dog.setName(name);
//        dog.setBreed(breed);
//        dog.setDateOfBirth(birth);
//        dog.setGender(gender);
//        dog.setOwner(sampleOwner);
//
//        return dog;
//    }
//
//    /**
//     * Creates DTO for the dog
//     * @param name Dog's name
//     * @param breed Dog's breed
//     * @param birth Date of birth
//     * @param gender Dog's gender
//     * @return Dog DTO
//     */
//    private DogDTO createDogDTO(String name, String breed, Date birth, Gender gender) {
//        return new DogDTO(name, breed, birth, gender);
//    }
//
//
//
//
//    @Test
//    public void testGetDogById() {
//        final Long id = 7L;
//        DogFacade dogFacade = new DogFacadeImpl();
//        Dog dog1 = createDog("Alik", "Labrador", Date.valueOf("2010-04-02"), Gender.MALE);
//
//
//
//    }
//

    /*
    DogCreateDTO dogDTO = new DogCreateDTO();
    dogDTO.setName("Ghia");
        dogDTO.setBreed("Australian shepherd");
        dogDTO.setDateOfBirth(Date.valueOf("2017-11-22"));
        dogDTO.setGender(Gender.FEMALE);*/



}
