package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer findById(Long id);
    Customer create(Customer customer);
    void delete(Customer customer);
    void update(Customer customer);
    List<Customer> findAll();
    List<Customer> getAllMatchingSurname(String surname);
    List<Customer> getAllMatchingPhoneNumber(String phoneNumber);
    List<Dog> getAllDogsOfCustomer(Long customerId);
    Customer getOwnerOfDog(Long dogId);

}
