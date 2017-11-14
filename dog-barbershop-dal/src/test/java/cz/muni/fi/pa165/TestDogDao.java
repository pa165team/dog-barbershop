package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.entity.Dog;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import cz.muni.fi.pa165.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.transaction.Transactional;

/**
 * Test class for Dog DAO
 * @author Lucie Kolarikova
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestDogDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private DogDao dogDao;

    /**
     * Creates a dog
     * @return dog
     */
    private Dog createADog() {
        Dog dog = new Dog();

        dog.setName("Alik");
        dog.setBreed("Labrador");
        dog.setDateOfBirth(java.sql.Date.valueOf("2010-04-02"));
        dog.setGender(Gender.MALE);

        dogDao.create(dog);
        return dog;
    }

    /**
     * Creates three dogs (2 female, 1 male)
     * @return list of dogs
     */
    private List<Dog> createThreeDogs() {
        Dog brenda = new Dog();
        brenda.setName("Brenda");
        brenda.setBreed("GermanShepherd");
        brenda.setDateOfBirth(Date.valueOf("2014-07-15"));
        brenda.setGender(Gender.FEMALE);
        dogDao.create(brenda);

        Dog betty = new Dog();
        betty.setName("Betty");
        betty.setBreed("Poodle");
        betty.setDateOfBirth(Date.valueOf("2017-05-20"));
        betty.setGender(Gender.FEMALE);
        dogDao.create(betty);

        Dog marley = new Dog();
        marley.setName("Marley");
        marley.setBreed("GoldenRetriever");
        marley.setDateOfBirth(Date.valueOf("2007-12-09"));
        marley.setGender(Gender.MALE);
        dogDao.create(marley);


        return Arrays.asList(brenda, betty, marley);
    }

    @Test
    public void storeAndReceiveADog() {
        Dog dog = createADog();

        List<Dog> dogs = dogDao.findAll();
        Assert.assertEquals(dogs.size(), 1);

        Dog daoDog = dogs.get(0);
        Assert.assertTrue(dog.equals(daoDog));
    }

    @Test
    public void storeAndDeleteADog() {
        Dog dog = createADog();

        List<Dog> dogsBeforeDeleting = dogDao.findAll();
        Assert.assertEquals(dogsBeforeDeleting.size(), 1);
        
        dogDao.delete(dog);
        
        List<Dog> dogsAfterDeleting = dogDao.findAll();
        Assert.assertEquals(dogsAfterDeleting.size(), 0);
    }

    @Test
    public void findDogById() {
        Dog dog = createADog();

        Dog foundDog = dogDao.findById(dog.getId());
        Assert.assertTrue(dog.equals(foundDog));
    }

    @Test
    public void findNonexistentDog() {
        createADog();

        Dog foundDog = dogDao.findById(-1L);
        Assert.assertEquals(foundDog, null);
    }

    @Test
    public void findAllFemaleDogs() {
        createThreeDogs();

        List<Dog> dogs = dogDao.findAllOfGender(Gender.FEMALE);
        Assert.assertEquals(dogs.size(), 2);
    }

    @Test
    public void findAllMaleDogs() {
        createThreeDogs();

        List<Dog> dogs = dogDao.findAllOfGender(Gender.MALE);
        Assert.assertEquals(dogs.size(), 1);
    }
    
    @Test
    public void updateDogGender() {
        Dog maleDog = createADog();
        maleDog.setGender(Gender.FEMALE);
        dogDao.update(maleDog);
        List<Dog> dogs = dogDao.findAllOfGender(Gender.FEMALE);
        Assert.assertEquals(dogs.size(), 1);
    }

}
