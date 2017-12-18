package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.RootWebContext;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.utils.Address;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Martin Kuchar 433499
 */

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class CustomersControllerTest extends AbstractTestNGSpringContextTests {
    @Mock
    private CustomerFacade customerFacade;

    @Autowired
    @InjectMocks
    private CustomersController customersController;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(customersController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        doReturn(Collections.unmodifiableList(this.createCustomers())).when(customerFacade).getAllCustomers();

        mockMvc.perform(get("/customers")).andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[?(@.id==1)].surname").value("Mrkvicka"))
            .andExpect(jsonPath("$.[?(@.id==2)].surname").value("Jozinova"));
    }

    @Test
    public void getValidCustomer() throws Exception {
        List<CustomerDTO> customers = this.createCustomers();

        doReturn(customers.get(0)).when(customerFacade).getCustomerById(1l);
        doReturn(customers.get(1)).when(customerFacade).getCustomerById(2l);

        mockMvc.perform(get("/customers/1")).andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.surname").value("Mrkvicka"));

        mockMvc.perform(get("/customers/2")).andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.surname").value("Jozinova"));

    }

    @Test
    public void getValidPhoneNumberCustomers() throws Exception {
        List<CustomerDTO> customers = this.createCustomers();

        doReturn(Arrays.asList(customers.get(0))).when(customerFacade).getAllCustomersMatchingPhoneNumber("123456789");

        mockMvc.perform(get("/customers/by_number/123456789")).andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[?(@.id==1)].surname").value("Mrkvicka"));
    }

    // TODO: fix this test
//    @Test
//    public void getValidSurnameCustomers() throws Exception{
//        List<CustomerDTO> customers = this.createCustomers();
//
//        doReturn(Arrays.asList(customers.get(1))).when(customerFacade).getAllCustomersMatchingPhoneNumber("123456789");
//
//        mockMvc.perform(get("/customers/by_surname/Jozinova")).andExpect(status().isOk())
//            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[?(@.surname==Jozinova)].id").value(2l));
//    }

    @Test
    public void getInvalidCustomer() throws Exception {
        doReturn(null).when(customerFacade).getCustomerById(1l);

        mockMvc.perform(get("/customers/1")).andExpect(status().is4xxClientError());
    }

    // TODO: fix this test
//    @Test
//    public void getInvalidPhoneNumberCustomers() throws Exception {
//        doReturn(null).when(customerFacade).getAllCustomersMatchingPhoneNumber("192837465");
//
//        mockMvc.perform(get("/customers/by_number/192837465")).andExpect(status().is4xxClientError());
//    }

    // TODO: fix this test
//    @Test
//    public void getInvalidSurnameCustomers() throws Exception {
//        doReturn(null).when(customerFacade).getAllCustomersMatchingSurname("RandomSurname");
//
//        mockMvc.perform(get("/customers/by_surname/RandomSurname")).andExpect(status().is4xxClientError());
//    }

    private List<CustomerDTO> createCustomers() {
        CustomerDTO c1 = new CustomerDTO();
        c1.setId(1L);
        c1.setName("Jozko");
        c1.setSurname("Mrkvicka");
        c1.setAddress(new Address("City", "Street", 47));
        c1.setPhoneNumber("123456789");
        c1.setDogs(new ArrayList<>());

        CustomerDTO c2 = new CustomerDTO();
        c2.setId(2L);
        c2.setName("Mrkva");
        c2.setSurname("Jozinova");
        c2.setAddress(new Address("Village", "WithoutStreets", 69));
        c2.setPhoneNumber("987654321");
        c2.setDogs(new ArrayList<>());

        return Arrays.asList(c1, c2);
    }
}
