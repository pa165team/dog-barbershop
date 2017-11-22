package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.dao.DogDao;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import org.dozer.inject.Inject;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

@Service
public class DogServiceImpl implements DogService {

    @Inject
    private DogDao dogDao;

    @Inject
    private CustomerDao customerDao;

    @Override
    public Dog findById(Long id) {
        return dogDao.findById(id);
    }

    @Override
    public List<Dog> findAll() {
        return dogDao.findAll();
    }

    @Override
    public void create(Dog dog, Long ownerId) {
        Customer owner = customerDao.findById(ownerId);
        dog.setOwner(owner);
        owner.addDog(dog);
        dogDao.create(dog);
        customerDao.update(owner);
    }

    @Override
    public void remove(Dog dog, Long ownerId) {
        Customer owner = customerDao.findById(ownerId);
        owner.removeDog(dog);
        dogDao.delete(dog);
        customerDao.update(owner);
    }

    @Override
    public List<Dog> findAllOfGender(Gender gender) {
        return dogDao.findAllOfGender(gender);
    }

    @Override
    public void update(Dog dog) {
        dogDao.update(dog);
    }

    @Override
    public Dog drawRandomDog() {
        Dog luckyDog = dogDao.getRandomlyDeterminedDogByLot();
        luckyDog.setHasDiscount(true);
        dogDao.update(luckyDog);
        return luckyDog;
    }
}
