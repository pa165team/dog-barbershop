package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.RootWebContext;
import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author Martin Kuchar 433499
 */

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class MainControllerTest extends AbstractTestNGSpringContextTests {
    private MockMvc mockMvc;

    @BeforeClass
    public void setup(){
        mockMvc = standaloneSetup(new MainController()).build();
    }

    @Test
    public void mainControllerTest() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("services_uri").value(ApiUris.ROOT_URI_SERVICES))
            .andExpect(jsonPath("customers_uri").value(ApiUris.ROOT_URI_CUSTOMERS))
            .andExpect(jsonPath("dogs_uri").value(ApiUris.ROOT_URI_DOGS))
            .andExpect(jsonPath("employees_uri").value(ApiUris.ROOT_URI_EMPLOYEES));
    }
}
