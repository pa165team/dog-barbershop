package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.ServiceType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    // TODO

    @Override
    public ServiceType create(ServiceType serviceType) {
        return null;
    }

    @Override
    public ServiceType findById(Long id) {
        return null;
    }

    @Override
    public List<ServiceType> findAll() {
        return null;
    }

    @Override
    public void changeDescription(ServiceType serviceType, String newDescription) {

    }

    @Override
    public void changePricePerHour(ServiceType serviceType, BigDecimal newPricePerHour) {

    }
}
