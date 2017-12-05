package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;
import static org.mockito.Mockito.verify;

/**
 * @author Lucie Kolarikova
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class DogServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CustomerDao customerDao;

    @Mock
    private DogDao dogDao;

    @Autowired
    @InjectMocks
    private DogService dogService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Dog getADog() {
        Dog dog = new Dog();
        dog.setName("Alik");
        dog.setBreed("Labrador");
        dog.setDateOfBirth(Date.valueOf("2010-04-02"));
        dog.setGender(Gender.MALE);

        return dog;
    }


    @Test
    public void testFindById(){
        Long id = 7L;
        dogService.findById(id);
        verify(dogDao).findById(id);
    }

    @Test
    public void testFindAll(){
        dogService.findAll();
        verify(dogDao).findAll();
    }

    @Test
    public void testCreate(){
        Dog dog = getADog();
        dogService.create(dog);
        verify(dogDao).create(dog);
    }


    @Test
    public void testRemove(){
        Dog dog = getADog();
        dogService.remove(dog);
        verify(dogDao).delete(dog);
    }

    @Test
    public void testFindAllOfGender(){
        Gender gender = Gender.FEMALE;
        dogService.findAllOfGender(gender);
        verify(dogDao).findAllOfGender(gender);
    }

    @Test
    public void testUpdate(){
        Dog dog = getADog();
        dogService.update(dog);
        verify(dogDao).update(dog);
    }

    @Test
    public void testDrawRandomDog(){
        dogService.drawRandomDog();
        verify(dogDao).getRandomlyDeterminedDogByLot();
    }


}
