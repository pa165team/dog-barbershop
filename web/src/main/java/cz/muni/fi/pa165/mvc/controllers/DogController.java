package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.dog.DogCreateDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.dto.dog.DogUpdateDTO;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.facade.DogFacade;
import cz.muni.fi.pa165.facade.ServiceRecordFacade;
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
import java.util.ArrayList;
import java.util.List;

/**
 * SpringMVC Controller for administrating dogs.
 *
 * @author Martin Kuchar 433499
 **/
@Controller
@RequestMapping("/dogs")
public class DogController {

    final static Logger log = LoggerFactory.getLogger(DogController.class);

    @Autowired
    private DogFacade dogFacade;

    @Autowired
    private CustomerFacade customerFacade;

    @Autowired
    private ServiceRecordFacade serviceRecordFacade;

    /**
     * Shows a list of dogs with the ability to add, delete, edit or show details of particular dogs.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/{filter}", method = RequestMethod.GET)
    public String list(@PathVariable String filter, Model model) {
        List<DogDTO> dogs;
        switch(filter){
            case "males":
                dogs = dogFacade.getAllDogsOfGender(Gender.MALE);
                break;
            case "females":
                dogs = dogFacade.getAllDogsOfGender(Gender.FEMALE);
                break;
            default:
                dogs = dogFacade.getAllDogs();
        }
        model.addAttribute("allDogs", dogs);
        model.addAttribute("filter", filter);
        return "dogs/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createNewDog(Model model) {
        log.debug("createNewDog()");

        model.addAttribute("dogCreate", new DogCreateDTO());
        model.addAttribute("allCustomers", customerFacade.getAllCustomers());
        model.addAttribute("genders", allGenders());

        return "dogs/create";
    }

    private List<Gender> allGenders() {
        List<Gender> genders = new ArrayList<>();
        genders.add(Gender.MALE);
        genders.add(Gender.FEMALE);
        return genders;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
        @Valid @ModelAttribute("dogCreate") DogCreateDTO formBean,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriBuilder
    ) {
        log.debug("create(formBean={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            //model.addAttribute("dogCreate", new DogCreateDTO());
            model.addAttribute("allCustomers", customerFacade.getAllCustomers());
            model.addAttribute("genders", allGenders());
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "dogs/create";
        }

        Long id = dogFacade.createDog(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Dog " + id + " was created.");
        return "redirect:" + uriBuilder.path("/dogs/all").toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        DogDTO dog = dogFacade.getDogById(id);
        DogUpdateDTO updateDTO = new DogUpdateDTO(dog.getName(), dog.getBreed(), dog.getDateOfBirth(), dog.getGender());
        model.addAttribute("dogToUpdate", updateDTO);
        model.addAttribute("dogId", dog.getId());
        model.addAttribute("genders", allGenders());
        //model.addAttribute("allCustomers", customerFacade.getAllCustomers());
        return "dogs/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(
        @Valid @ModelAttribute("dogToUpdate") DogUpdateDTO dogEdit,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriComponentsBuilder,
        @PathVariable long id)
    {
        DogDTO originalDog = dogFacade.getDogById(id);
        originalDog.setName(dogEdit.getName());
        originalDog.setBreed(dogEdit.getBreed());
        originalDog.setDateOfBirth(dogEdit.getDateOfBirth());
        originalDog.setGender(dogEdit.getGender());

        if (bindingResult.hasErrors()) {
            model.addAttribute("dogId", id);
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "dogs/edit";
        }
        dogFacade.updateDog(originalDog);
        redirectAttributes.addFlashAttribute("alert_success", "Dog was successfully updated.");
        return "redirect:" + uriComponentsBuilder.path("/dogs/all").build().encode().toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("dog", dogFacade.getDogById(id));
        model.addAttribute("serviceRecords", serviceRecordFacade.getServiceRecordsByDog(id));
        return "dogs/detail";
    }
}
