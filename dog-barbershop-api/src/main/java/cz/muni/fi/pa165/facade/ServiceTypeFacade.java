package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeCreateDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;

import java.util.List;

/**
 * @author Jan Kalfus
 */
public interface ServiceTypeFacade {

    Long createServiceType(ServiceTypeCreateDTO serviceType);

    void changeDescription(DescriptionChangeDTO newDescription);

    void changePricePerHour(PricePerHourChangeDTO newPricePerHour);

    List<ServiceTypeDTO> getAllServiceTypes();
}
