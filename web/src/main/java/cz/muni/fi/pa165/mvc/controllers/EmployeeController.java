package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.employee.EmployeeCreateDTO;
import cz.muni.fi.pa165.facade.EmployeeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * SpringMVC Controller for administering employees.
 *
 * @author Daniel MudrĂ­k (433655)
**/
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeFacade employeeFacade;

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("employees", employeeFacade.getAllEmployees());
        return "employees/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newEmployee(Model model) {
        log.debug("new()");
        model.addAttribute("employeeCreate", new EmployeeCreateDTO());
        return "employees/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("employeeCreate") EmployeeCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "employees/new";
        }
        //create employee
        Long id = employeeFacade.createEmployee(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Employee " + id + " was created");
        return "redirect:" + uriBuilder.path("/employees").toUriString();
    }
}
