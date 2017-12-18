package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordCreateDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import cz.muni.fi.pa165.facade.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * SpringMVC Controller for administrating dogs.
 *
 * @author Martin Kuchar 433499
 **/
@Controller
@RequestMapping("/records")
public class ServiceRecordController {

    final static Logger log = LoggerFactory.getLogger(ServiceRecordController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    @Autowired
    private DogFacade dogFacade;

    @Autowired
    private ServiceRecordFacade serviceRecordFacade;

    @Autowired
    private ServiceTypeFacade serviceTypeFacade;

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new/{dogId}", method = RequestMethod.GET)
    public String createNewService(@PathVariable long dogId, Model model) {
        log.debug("createNewServiceRecord()");

        DogDTO dog = dogFacade.getDogById(dogId);

        model.addAttribute("recordCreate", new ServiceRecordCreateDTO());
        model.addAttribute("allEmployees", employeeFacade.getAllEmployees());
        model.addAttribute("allServiceTypes", serviceTypeFacade.getAllServiceTypes());
        model.addAttribute("dogName", dog.getName());
        model.addAttribute("dogId", dogId);

        return "serviceRecords/create";
    }

    @RequestMapping(value = "/create/{dogId}", method = RequestMethod.POST)
    public String create(
        @Valid @ModelAttribute("recordCreate") ServiceRecordCreateDTO formBean,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriBuilder,
        @PathVariable long dogId
    ) {
        formBean.setDogId(dogId);
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
            DogDTO dog = dogFacade.getDogById(dogId);
            //model.addAttribute("recordCreate", new ServiceRecordCreateDTO());
            model.addAttribute("allEmployees", employeeFacade.getAllEmployees());
            model.addAttribute("allServiceTypes", serviceTypeFacade.getAllServiceTypes());
            //model.addAttribute("dogName", dog.getName());
            //model.addAttribute("dogId", dogId);
            return "serviceRecords/create";
        }

        Long id = serviceRecordFacade.createServiceRecord(formBean);

        DogDTO dogOfThisRecord = dogFacade.getDogById(formBean.getDogId());

        redirectAttributes.addFlashAttribute(
            "alert_success", "Service " + id + " was successfully ordered for " +
                dogOfThisRecord.getName() + ".");
        return "redirect:" + uriBuilder.path("/dogs/detail/" + dogOfThisRecord.getId()).toUriString();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getFromLastWeek(Model model) {
        List<ServiceRecordDTO> records = serviceRecordFacade.getServiceRecordsFromLastWeek();
        model.addAttribute("records", records);
        return "serviceRecords/list";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<ServiceRecordDTO> records = serviceRecordFacade.getAll();
        model.addAttribute("records", records);
        model.addAttribute("all", true);
        return "serviceRecords/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String get(@PathVariable long id, Model model) {
        ServiceRecordDTO record = serviceRecordFacade.getById(id);
        model.addAttribute("record", record);
        return "serviceRecords/detail";
    }
}
