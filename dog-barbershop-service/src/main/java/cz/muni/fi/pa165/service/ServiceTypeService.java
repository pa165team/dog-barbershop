package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.ServiceType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public interface ServiceTypeService {

    ServiceType create(ServiceType serviceType);

    ServiceType findById(Long id);

    List<ServiceType> findAll();

    void changeDescription(ServiceType serviceType, String newDescription);

    void changePricePerHour(ServiceType serviceType, BigDecimal newPricePerHour);
}
