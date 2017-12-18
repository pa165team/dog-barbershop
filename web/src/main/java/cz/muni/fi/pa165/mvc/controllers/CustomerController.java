package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.facade.DogFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * @author Lucie Kolarikova
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    final static Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    private CustomerFacade customerFacade;
    @Autowired
    private DogFacade dogFacade;

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerFacade.getAllCustomers());
        return "customers/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        logger.debug("new()");

        model.addAttribute("customerCreate", new CustomerCreateDTO());
        return "customers/new";
    }

    /*
     * For creating new customer
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("customerCreate") CustomerCreateDTO formBean,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        logger.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                logger.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                logger.trace("FieldError: {}", fe);
            }
            return "customers/new";
        }
        //create customer
        Long id = customerFacade.createCustomer(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Customer " + id + " was created.");
        return "redirect:" + uriBuilder.path("/customers").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("customerToUpdate", customerFacade.getCustomerById(id));
        return "customers/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("customerToUpdate") CustomerDTO customerToUpdate,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriComponentsBuilder,
        @PathVariable long id) {

        customerToUpdate.setId(id);

        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "customers/edit";
        }
        customerFacade.updateCustomer(customerToUpdate);
        redirectAttributes.addFlashAttribute("alert_success", "Customer was successfully updated.");
        return "redirect:" + uriComponentsBuilder.path("/customers").build().encode().toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerFacade.getCustomerById(id));
        model.addAttribute("dogs", customerFacade.getAllDogsOfCustomer(id));
        return "customers/detail";
    }
}
