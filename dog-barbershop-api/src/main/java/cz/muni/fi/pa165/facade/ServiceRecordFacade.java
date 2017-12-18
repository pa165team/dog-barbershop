package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordCreateDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jan Kalfus
 */
public interface ServiceRecordFacade {
    Long createServiceRecord(ServiceRecordCreateDTO dto);
    List<ServiceRecordDTO> getServiceRecordsByDog(Long dogId);
    List<ServiceRecordDTO> getServiceRecordsFromLastWeek();
    BigDecimal getTurnoverForLastMonth();
    List<ServiceRecordDTO> getAll();
    ServiceRecordDTO getById(Long id);
}
