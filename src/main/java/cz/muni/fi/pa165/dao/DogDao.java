package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;

import java.util.List;

public interface DogDao {
    Dog findById(Long id);
    void create(Dog dog);
    void delete(Dog dog);
    List<Dog> findAll();
    Dog findByName(String name);
    List<Dog> findAllOfGender(Gender gender);
}
