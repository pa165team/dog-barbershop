package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import cz.muni.fi.pa165.facade.ServiceTypeFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
