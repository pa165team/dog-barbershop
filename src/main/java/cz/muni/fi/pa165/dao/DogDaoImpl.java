package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DogDaoImpl implements DogDao{

    @PersistenceContext
    private EntityManager em;

    public Dog findById(Long id) {
        return null;
    }

    public void create(Dog dog) {
        //
    }

    public void delete(Dog dog) {
        //
    }

    public List<Dog> findAll() {
        return null;
    }

    public Dog findByName(String name) {
        return null;
    }

    public List<Dog> findAllOfGender(Gender gender) {
        return null;
    }
}
