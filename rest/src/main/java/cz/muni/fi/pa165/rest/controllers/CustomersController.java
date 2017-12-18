package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Martin Kuchar 433499
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_CUSTOMERS)
public class CustomersController {

    final static Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Inject
    private CustomerFacade customerFacade;

    /**
     * get all customers
     * @return list of CustomerDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getCustomers() {
        logger.debug("rest getCustomers()");
        return customerFacade.getAllCustomers();
    }

    /**
     *
     * Get one customer specified by id
     *
     * @param id identifier for the customer
     * @return CustomerDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getCustomer({})", id);
        CustomerDTO customerDTO = customerFacade.getCustomerById(id);
        if (customerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return customerDTO;
    }

    /**
     * Retrieves all customers with given surname
     * @param surname
     * @return List of CustomerDTOs
     */
    @RequestMapping(value = "/by_surname/{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getCustomersWithSurname(@PathVariable("surname") String surname) {
        logger.debug("rest getCustomersWithSurname({})", surname);
        return customerFacade.getAllCustomersMatchingSurname(surname);
    }

    /**
     * Retrieves all customers with given phone number
     * @param number
     * @return List of CustomerDTOs
     */
    @RequestMapping(value = "/by_number/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getCustomersWithPhoneNumber(@PathVariable("number") String number){
        logger.debug("rest getCustomersWithPhoneNumber({})", number);
        return customerFacade.getAllCustomersMatchingPhoneNumber(number);
    }
    
     /**
     * Delete one customer by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id identifier for customer
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteCustomer({})", id);

        CustomerDTO cust = customerFacade.getCustomerById(id);
        if (cust == null) {
            throw new ResourceNotFoundException();
        }
        customerFacade.deleteCustomer(cust);
    }

    /**
     * Create a new customer by POST method
     * curl -X POST -i -H "Content-Type: application/json" --data 
     * '{"name":"Jozinko","surname":"Petuniak","address":{"city":"UPLNE","street":"NOVA ADRESA","number":911},"phoneNumber":"123456789"}' 
     * http://localhost:8080/pa165/rest/customers/create
     * 
     * @param customer CustomerCreateDTO with required fields for creation
     * @return the created customer CustomerDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO createCustomer(@RequestBody CustomerCreateDTO customer) throws Exception {

        logger.debug("rest createCustomer()");

        try {
            Long id = customerFacade.createCustomer(customer);
            return customerFacade.getCustomerById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

  /**
     * Retrieves all dogs of a single customer
     * @param customerId
     * @return List of DogDTOs
     */
    @RequestMapping(value = "/{customerId}/allDogs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DogDTO> getAllDogsOfCustomer(@PathVariable("customerId") Long customerId) {
        logger.debug("rest getAllDogsOfCustomer({})", customerId);
        return customerFacade.getAllDogsOfCustomer(customerId);
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerCreateDTO customer) throws Exception {

        logger.debug("rest createCustomer()");
        
        CustomerDTO cust = new CustomerDTO();
        cust.setId(id);
        cust.setAddress(customer.getAddress());
        cust.setName(customer.getName());
        cust.setPhoneNumber(customer.getPhoneNumber());
        cust.setSurname(customer.getSurname());
        
        try {
            Long customerId = customerFacade.updateCustomer(cust);
            return customerFacade.getCustomerById(customerId);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    
}
