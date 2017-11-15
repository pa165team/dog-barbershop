package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.CustomerDTO;

import java.util.List;

public interface CustomerFacade {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById();
    List<CustomerDTO> getAllCustomersMatchingSurname(String surname);
    List<CustomerDTO> getAllCustomersMatchingPhoneNumber(String phoneNumber);
    Long createCustomer(CustomerCreateDTO newCustomer);
    void deleteCustomer(CustomerDTO customer);
}
