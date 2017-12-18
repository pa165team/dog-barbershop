package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.ServiceRecordFacade;
import cz.muni.fi.pa165.facade.ServiceTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jan Kalfus
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private ServiceRecordFacade serviceRecordFacade;

    @Autowired
    private ServiceTypeFacade serviceTypeFacade;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTurnover(Model model) {
        model.addAttribute("turnover", serviceRecordFacade.getTurnoverForLastMonth());
        model.addAttribute("bestServiceType", serviceTypeFacade.getMostProfitableServiceTypeUntilNow());
        return "finance/stats";
    }
}
