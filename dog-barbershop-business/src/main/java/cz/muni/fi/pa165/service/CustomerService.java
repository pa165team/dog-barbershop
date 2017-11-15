package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer findById(Long id);
    List<Customer> findAll();
    void create(Customer customer);
    void remove(Customer customer);
    List<Customer> getAllMatchingSurname(String surname);
    List<Customer> getAllMatchingPhoneNumber(String phoneNumber);
}
