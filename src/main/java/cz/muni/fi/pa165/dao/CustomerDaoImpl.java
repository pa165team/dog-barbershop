package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Lucie Kolarikova
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class)
            .getResultList();
    }

    @Override
    public List<Customer> getAllMatchingSurname(String surname) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.surname LIKE :surname", Customer.class)
            .setParameter("surname", "%" + surname + "%")
            .getResultList();
    }

    @Override
    public List<Customer> getAllMatchingPhoneNumber(String phoneNumber) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber LIKE :phoneNumber", Customer.class)
            .setParameter("phoneNumber", "%" + phoneNumber + "%")
            .getResultList();

    }
}
