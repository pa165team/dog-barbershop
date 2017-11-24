package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

@Service
public interface DogService {
    Dog findById(Long id);
    List<Dog> findAll();
    void create(Dog dog);
    void remove(Dog dog);
    List<Dog> findAllOfGender(Gender gender);
    void update(Dog dog);
    Dog drawRandomDog();
}
