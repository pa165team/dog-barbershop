package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordCreateDTO;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.entity.ServiceRecord;
import cz.muni.fi.pa165.entity.ServiceType;
import cz.muni.fi.pa165.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Service
@Transactional
public class ServiceRecordFacadeImpl implements ServiceRecordFacade {

    private static final double MINUTES_IN_HOUR = 60.0;

    private ServiceRecordService serviceRecordService;
    private ServiceTypeService serviceTypeService;
    private DogService dogService;
    private EmployeeService employeeService;
    private BeanMappingService beanMappingService;

    @Autowired
    public ServiceRecordFacadeImpl(ServiceRecordService serviceRecordService, ServiceTypeService serviceTypeService, DogService dogService, EmployeeService employeeService, BeanMappingService beanMappingService) {
        this.serviceRecordService = serviceRecordService;
        this.serviceTypeService = serviceTypeService;
        this.dogService = dogService;
        this.employeeService = employeeService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public Long createServiceRecord(ServiceRecordCreateDTO dto) {
        ServiceType type = serviceTypeService.findById(dto.getServiceTypeId());
        Dog dog = dogService.findById(dto.getDogId());
        Employee emp = employeeService.findById(dto.getEmployeeId());

        ServiceRecord record = new ServiceRecord();
        record.setServiceType(type);
        record.setEmployee(emp);
        record.setDog(dog);
        record.setDateProvided(new Date());
        record.setLengthMinutes(dto.getLengthMinutes());
        record.setActualPrice(calculateActualPrice(type, dto.getLengthMinutes()));

        dog.addServiceRecord(record);
        emp.addServiceRecord(record);

        ServiceRecord created = serviceRecordService.create(record);

        return created.getId();
    }

    private BigDecimal calculateActualPrice(ServiceType type, Integer lengthMinutes) {
        double lengthHours = lengthMinutes / MINUTES_IN_HOUR;
        return type.getPricePerHour().multiply(new BigDecimal(lengthHours)).setScale(0, RoundingMode.HALF_UP);
    }

    @Override
    public List<ServiceRecordDTO> getServiceRecordsByDog(Long dogId) {
        Dog dog = dogService.findById(dogId);
        List<ServiceRecord> serviceRecordsByDog = serviceRecordService.getServiceRecordsByDog(dog);
        return beanMappingService.mapTo(serviceRecordsByDog, ServiceRecordDTO.class);
    }

    @Override
    public List<ServiceRecordDTO> getServiceRecordsFromLastWeek() {
        List<ServiceRecord> recordsFromLastWeek = serviceRecordService.getServiceRecordsFromLastWeek();
        return beanMappingService.mapTo(recordsFromLastWeek, ServiceRecordDTO.class);
    }

    @Override
    public BigDecimal getTurnoverForLastMonth() {
        return serviceRecordService.getTurnoverForLastMonth();
    }
}
