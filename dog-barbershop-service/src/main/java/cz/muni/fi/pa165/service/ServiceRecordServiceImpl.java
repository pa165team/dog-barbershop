package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    private ServiceRecordDao serviceRecordDao;

    @Autowired
    public ServiceRecordServiceImpl(ServiceRecordDao serviceRecordDao) {
        this.serviceRecordDao = serviceRecordDao;
    }

    @Override
    public ServiceRecord create(ServiceRecord s) {
        serviceRecordDao.create(s);
        return s;
    }

    @Override
    public List<ServiceRecord> getServiceRecordsByDog(Dog dog) {
        return serviceRecordDao.getRecordsByDog(dog);
    }
}
