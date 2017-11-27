package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ServiceRecordDao;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    private ServiceRecordDao serviceRecordDao;
    private TimeService timeService;

    @Autowired
    public ServiceRecordServiceImpl(ServiceRecordDao serviceRecordDao, TimeService timeService) {
        this.serviceRecordDao = serviceRecordDao;
        this.timeService = timeService;
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

    @Override
    public List<ServiceRecord> getServiceRecordsFromLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeService.getCurrentTime());
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date lastWeek = calendar.getTime();
        return serviceRecordDao.getServicesProvidedBetween(lastWeek, timeService.getCurrentTime());
    }
}
