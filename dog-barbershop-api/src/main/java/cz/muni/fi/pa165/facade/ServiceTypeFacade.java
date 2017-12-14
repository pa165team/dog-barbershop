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

    Long createServiceType(ServiceTypeCreateDTO dto);

    void changeDescription(DescriptionChangeDTO dto);

    void changePricePerHour(PricePerHourChangeDTO dto);

    List<ServiceTypeDTO> getAllServiceTypes();

    ServiceTypeDTO getServiceType(long id);

    void update(ServiceTypeDTO dto);
}
