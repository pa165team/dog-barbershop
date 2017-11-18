package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for Dog DAO
 *
 * @author Lucie Kolarikova
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestDogDao extends AbstractTestNGSpringContextTests {

    @Autowired
    private DogDao dogDao;

    @Autowired
    private CustomerDao customerDao;

    private Customer sampleOwner;

    @BeforeMethod
    private void beforeEvery() {
        sampleOwner = createSampleOwner();
    }

    private Customer createSampleOwner() {
        Customer owner = new Customer();
        owner.setName("Jan");
        owner.setSurname("Novák");
        owner.setAddress(new Address("Brno", "Náměstí svobody", 20));
        owner.setPhoneNumber("123456789");
        customerDao.create(owner);
        return owner;
    }

    @PersistenceContext
    private EntityManager em;

    private Dog createSingleDog() {
        return createSingleDog("Alik", "Labrador", Date.valueOf("2010-04-02"), Gender.MALE);
    }

    private Dog createSingleDog(String name, String breed, Date birth, Gender gender) {
        Dog dog = new Dog();

        dog.setName(name);
        dog.setBreed(breed);
        dog.setDateOfBirth(birth);
        dog.setGender(gender);
        dog.setOwner(sampleOwner);

        dogDao.create(dog);
        return dog;
    }

    /**
     * Creates three dogs (2 female, 1 male)
     *
     * @return list of dogs
     */
    private List<Dog> createThreeDogs() {
        Dog brenda = createSingleDog("Brenda", "GermanShepherd", Date.valueOf("2014-07-15"), Gender.FEMALE);
        Dog betty = createSingleDog("Betty", "Poodle", Date.valueOf("2017-05-20"), Gender.FEMALE);
        Dog marley = createSingleDog("Marley", "GoldenRetriever", Date.valueOf("2007-12-09"), Gender.MALE);
        return Arrays.asList(brenda, betty, marley);
    }

    @Test
    public void storeAndReceiveADog() {
        Dog dog = createSingleDog();

        List<Dog> dogs = dogDao.findAll();
        Assert.assertEquals(dogs.size(), 1);

        Dog daoDog = dogs.get(0);
        Assert.assertTrue(dog.equals(daoDog));
    }

    @Test
    public void storeAndDeleteADog() {
        Dog dog = createSingleDog();

        List<Dog> dogsBeforeDeleting = dogDao.findAll();
        Assert.assertEquals(dogsBeforeDeleting.size(), 1);

        dogDao.delete(dog);

        List<Dog> dogsAfterDeleting = dogDao.findAll();
        Assert.assertEquals(dogsAfterDeleting.size(), 0);
    }

    @Test
    public void findDogById() {
        Dog dog = createSingleDog();

        Dog foundDog = dogDao.findById(dog.getId());
        Assert.assertTrue(dog.equals(foundDog));
    }

    @Test
    public void findNonexistentDog() {
        createSingleDog();

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
        Dog maleDog = createSingleDog();
        em.detach(maleDog);
        maleDog.setGender(Gender.FEMALE);
        dogDao.update(maleDog);
        List<Dog> dogs = dogDao.findAllOfGender(Gender.FEMALE);
        Assert.assertEquals(dogs.size(), 1);
    }

}
