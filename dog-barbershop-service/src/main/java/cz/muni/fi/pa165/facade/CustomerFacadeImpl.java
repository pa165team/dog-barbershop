package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Lucie Kolarikova
 */

@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private CustomerService customerService;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return beanMappingService.mapTo(customerService.findById(id), CustomerDTO.class);
    }

    @Override
    public Long createCustomer(CustomerCreateDTO newCustomer) {
        Customer customer = new Customer();
        customer.setName(newCustomer.getName());
        customer.setSurname(newCustomer.getSurname());
        customer.setAddress(newCustomer.getAddress());
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customerService.create(customer);
        return customer.getId();
    }

    @Override
    public void deleteCustomer(CustomerDTO customer) {
        customerService.delete(beanMappingService.mapTo(customer, Customer.class));
    }

    @Override
    public Long updateCustomer(CustomerDTO customer) {
        customerService.update(beanMappingService.mapTo(customer, Customer.class));
        return customer.getId();
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return beanMappingService.mapTo(customerService.findAll(), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomersMatchingSurname(String surname) {
        return beanMappingService.mapTo(customerService.getAllMatchingSurname(surname), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomersMatchingPhoneNumber(String phoneNumber) {
        return beanMappingService.mapTo(customerService.getAllMatchingPhoneNumber(phoneNumber), CustomerDTO.class);
    }

}
