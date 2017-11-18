package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordCreateDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
@Transactional
public class ServiceRecordFacadeImpl implements ServiceRecordFacade {
    // TODO

    @Override
    public Long createServiceRecord(ServiceRecordCreateDTO serviceRecord) {
        return null;
    }

    @Override
    public List<ServiceRecordDTO> getServiceRecordsByDog(Long dogId) {
        return null;
    }
}
