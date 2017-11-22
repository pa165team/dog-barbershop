package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.CustomerDao;
import cz.muni.fi.pa165.entity.Customer;
import org.dozer.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucie Kolarikova
 */

@Service
public class CustomerServiceImpl implements CustomerService{

    @Inject
    private CustomerDao customerDao;


    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id);
    }


    @Override
    public void create(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public List<Customer> getAllMatchingSurname(String surname) {
        return customerDao.getAllMatchingSurname(surname);
    }

    @Override
    public List<Customer> getAllMatchingPhoneNumber(String phoneNumber) {
        return customerDao.getAllMatchingPhoneNumber(phoneNumber);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
