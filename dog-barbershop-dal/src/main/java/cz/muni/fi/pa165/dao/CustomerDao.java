package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Customer;

import java.util.List;

/**
 * DAO (Data Access Object) interface for Customer entity
 *
 * @author Lucie Kolarikova
 */
public interface CustomerDao {
    /**
     * Finds customer by id.
     *
     * @param id Id to search for.
     * @return Customer.
     */
    Customer findById(Long id);


    /**
     * Creates customer in database.
     *
     * @param customer Given customer to create.
     */
    void create(Customer customer);

    /**
     * Deletes customer in database.
     *
     * @param customer Given customer to delete.
     */
    void delete(Customer customer);


    /**
     * Finds all customers in database.
     *
     * @return List of customers.
     */
    List<Customer> findAll();


    /**
     * Searches for all customers matching given surname.
     *
     * @param surname Surname to search for.
     * @return List of customers matching given surname.
     */
    List<Customer> getAllMatchingSurname(String surname);


    /**
     * Searches for all customers matching given phone number.
     *
     * @param phoneNumber Phone number to search for.
     * @return List of customers matching given phone number.
     */
    List<Customer> getAllMatchingPhoneNumber(String phoneNumber);
}
