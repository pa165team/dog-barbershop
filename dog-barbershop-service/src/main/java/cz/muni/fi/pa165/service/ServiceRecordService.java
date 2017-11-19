package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
public interface ServiceRecordService {

    ServiceRecord create(ServiceRecord s);

    List<ServiceRecord> getServiceRecordsByDog(Dog dog);


}
