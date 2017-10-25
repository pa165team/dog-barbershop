package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import java.util.List;

/**
 * @author Martin Kuchár 433499
 */

public interface DogDao {

    /**
     * Finds dog by given ID.
     * @param id - id to obtain.
     * @return Dog.
     */
    Dog findById(Long id);

    /**
     * Creates dog in DB.
     * @param dog - dog to create.
     */
    void create(Dog dog);

    /**
     * Deletes dog from DB.
     * @param dog - dog to delete.
     */
    void delete(Dog dog);

    /**
     * Finds all stored dogs.
     * @return List of Dogs.
     */
    List<Dog> findAll();

    /**
     * Finds all dogs of given Gender
     * @param gender - gender to obtain.
     * @return List of Dogs.
     */
    List<Dog> findAllOfGender(Gender gender);
}
