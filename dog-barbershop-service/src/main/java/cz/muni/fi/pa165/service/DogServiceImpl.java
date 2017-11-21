package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.DogDao;
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

    @Override
    public Dog findById(Long id) {
        return dogDao.findById(id);
    }

    @Override
    public List<Dog> findAll() {
        return dogDao.findAll();
    }

    @Override
    public void create(Dog dog) {
        dogDao.create(dog);
    }

    @Override
    public void remove(Dog dog) {
        dogDao.delete(dog);
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
    public Dog getRandomDog() {
        return dogDao.getRandomlyDeterminedDogByLot();
    }
}
