package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicetype.DescriptionChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.PricePerHourChangeDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeCreateDTO;
import cz.muni.fi.pa165.dto.servicetype.ServiceTypeDTO;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.DogBarbershopServiceException;
import cz.muni.fi.pa165.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
@Transactional
public class ServiceTypeFacadeImpl implements ServiceTypeFacade {

    private ServiceTypeService serviceTypeService;
    private BeanMappingService beanMappingService;

    @Autowired
    public ServiceTypeFacadeImpl(ServiceTypeService serviceTypeService, BeanMappingService beanMappingService) {
        this.serviceTypeService = serviceTypeService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createServiceType(ServiceTypeCreateDTO dto) {
        ServiceType serviceType = beanMappingService.mapTo(dto, ServiceType.class);
        ServiceType created = serviceTypeService.create(serviceType);
        return created.getId();
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
        List<ServiceType> all = serviceTypeService.findAll();
        return beanMappingService.mapTo(all, ServiceTypeDTO.class);
    }

    @Override
    public ServiceTypeDTO getServiceType(long id) {
        ServiceType serviceType = serviceTypeService.findById(id);
        return beanMappingService.mapTo(serviceType, ServiceTypeDTO.class);
    }

    @Override
    public void update(ServiceTypeDTO dto) {
        ServiceType serviceType = serviceTypeService.findById(dto.getId());
        serviceType.setDescription(dto.getDescription());
        serviceType.setPricePerHour(dto.getPricePerHour());
    }

    @Override
    public ServiceTypeDTO getMostProfitableServiceTypeUntilNow(){
        List<ServiceType> allTypes = serviceTypeService.findAll();
        List<Double> sumOfPrices = new ArrayList<>();

        Double sumOfActual;
        for (ServiceType type : allTypes) {
            sumOfActual = 0d;
            for (ServiceRecord record : type.getServiceRecords()) {
                sumOfActual += record.getActualPrice().doubleValue();
            }
            sumOfPrices.add(sumOfActual);
        }
        Double maxPrice = getMaxValueInPricesArray(sumOfPrices);
        ServiceType bestType = allTypes.get(sumOfPrices.indexOf(maxPrice));
        return beanMappingService.mapTo(bestType, ServiceTypeDTO.class);
    }

    private Double getMaxValueInPricesArray(List<Double> list){
        Double max = 0d;
        for (Double price: list) {
            max = Math.max(max, price);
        }
        return max;
    }
}
