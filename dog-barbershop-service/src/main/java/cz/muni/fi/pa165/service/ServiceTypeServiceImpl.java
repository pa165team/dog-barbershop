package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceTypeDao;
import cz.muni.fi.pa165.entity.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private ServiceTypeDao serviceTypeDao;

    @Autowired
    public ServiceTypeServiceImpl(ServiceTypeDao serviceTypeDao) {
        this.serviceTypeDao = serviceTypeDao;
    }

    @Override
    public ServiceType create(ServiceType serviceType) {
        serviceTypeDao.create(serviceType);
        return serviceType;
    }

    @Override
    public ServiceType findById(Long id) {
        return serviceTypeDao.findById(id);
    }

    @Override
    public List<ServiceType> findAll() {
        return serviceTypeDao.findAll();
    }
}
