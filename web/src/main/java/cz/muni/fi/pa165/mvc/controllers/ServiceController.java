package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import cz.muni.fi.pa165.facade.ServiceTypeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Controller
@RequestMapping("/services")
public class ServiceController {

    final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ServiceTypeFacade serviceTypeFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTypes(Model model) {
        List<ServiceTypeDTO> serviceTypes = serviceTypeFacade.getAllServiceTypes();
        model.addAttribute("services", serviceTypes);
        logger.debug("======= Number of values: " + String.valueOf(serviceTypes.size()));
        return "services/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("serviceTypeEdit", serviceTypeFacade.getServiceType(id));
        return "services/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("serviceTypeEdit") ServiceTypeDTO serviceTypeEdit,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes,
                       UriComponentsBuilder uriComponentsBuilder,
                       @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "services/edit";
        }
        serviceTypeEdit.setId(id);
        serviceTypeFacade.update(serviceTypeEdit);
        redirectAttributes.addFlashAttribute("alert_success", "Service type was successfully updated");
        return "redirect:" + uriComponentsBuilder.path("/services").build().encode().toUriString();
    }
}
