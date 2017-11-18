package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeCreateDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
@Transactional
public class ServiceTypeFacadeImpl implements ServiceTypeFacade {
    // TODO


    @Override
    public Long createServiceType(ServiceTypeCreateDTO serviceType) {
        return null;
    }

    @Override
    public void changeDescription(DescriptionChangeDTO newDescription) {

    }

    @Override
    public void changePricePerHour(PricePerHourChangeDTO newPricePerHour) {

    }

    @Override
    public List<ServiceTypeDTO> getAllServiceTypes() {
        return null;
    }
}
