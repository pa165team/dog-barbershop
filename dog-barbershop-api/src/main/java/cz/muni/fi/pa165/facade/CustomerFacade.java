package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;

import java.util.List;

/**
 * @author Lucie Kolarikova
 */
public interface CustomerFacade {
    CustomerDTO getCustomerById(Long id);
    Long createCustomer(CustomerCreateDTO newCustomer);
    void deleteCustomer(CustomerDTO customer);
    Long updateCustomer(CustomerDTO customer);
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> getAllCustomersMatchingSurname(String surname);
    List<CustomerDTO> getAllCustomersMatchingPhoneNumber(String phoneNumber);
    List<DogDTO> getAllDogsOfCustomer(Long customerId);
}
