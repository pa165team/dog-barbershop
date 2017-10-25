package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Martin Kuchár 433499
 */

@Repository
public class DogDaoImpl implements DogDao{

    @PersistenceContext
    private EntityManager em;

    public Dog findById(Long id) {
        return em.find(Dog.class, id);
    }

    public void create(Dog dog) {
        em.persist(dog);
    }

    public void delete(Dog dog) {
        em.remove(dog);
    }

    public List<Dog> findAll() {
        return em.createQuery("select dog from Dog dog", Dog.class)
            .getResultList();
    }

    public List<Dog> findAllOfGender(Gender gender) {
        return em.createQuery("select dog from Dog dog where gender = :gender",
            Dog.class).setParameter(":gender", gender)
            .getResultList();
    }
}
