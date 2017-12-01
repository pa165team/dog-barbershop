package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import org.dozer.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping(value = "/{surname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getCustomersWithSurname(@PathVariable("surname") String surname) {
        logger.debug("rest getCustomersWithSurname({})", surname);
        return customerFacade.getAllCustomersMatchingSurname(surname);
    }

    /**
     * Retrieves all customers with given phone number
     * @param number
     * @return List of CustomerDTOs
     */
    @RequestMapping(value = "/{number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CustomerDTO> getCustomersWithPhoneNumber(@PathVariable("number") String number){
        logger.debug("rest getCustomersWithPhoneNumber({})", number);
        return customerFacade.getAllCustomersMatchingPhoneNumber(number);
    }
}
