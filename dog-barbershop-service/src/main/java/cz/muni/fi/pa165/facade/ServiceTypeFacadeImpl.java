package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeCreateDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.DogBarbershopServiceException;
import cz.muni.fi.pa165.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
@Transactional
public class ServiceTypeFacadeImpl implements ServiceTypeFacade {
    // TODO

    private ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeFacadeImpl(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @Override
    public Long createServiceType(ServiceTypeCreateDTO dto) {
        return null;
    }

    @Override
    public void changeDescription(DescriptionChangeDTO dto) {
        ServiceType type = serviceTypeService.findById(dto.getServiceTypeId());
        type.setDescription(dto.getDescription());
    }

    @Override
    public void changePricePerHour(PricePerHourChangeDTO dto) {
        if (!isGreaterThanZero(dto.getPricePerHour())) {
            throw new DogBarbershopServiceException("Price must be greater than 0!");
        }

        ServiceType type = serviceTypeService.findById(dto.getServiceTypeId());
        type.setPricePerHour(dto.getPricePerHour());
    }

    private boolean isGreaterThanZero(BigDecimal price) {
        return price.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public List<ServiceTypeDTO> getAllServiceTypes() {
        return null;
    }
}
