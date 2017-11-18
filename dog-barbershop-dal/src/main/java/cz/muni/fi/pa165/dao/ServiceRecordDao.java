package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.ServiceRecord;

import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
public interface ServiceRecordDao {
    ServiceRecord findById(Long id);

    void create(ServiceRecord serviceRecord);

    void update(ServiceRecord serviceRecord);

    void delete(ServiceRecord serviceRecord);

    List<ServiceRecord> findAll();

    /**
     * Gets services that were provided between two dates
     *
     * @param start First date
     * @param end   Second date
     * @return All matching services, or null if no services match.
     */
    List<ServiceRecord> getServicesProvidedBetween(Date start, Date end);
}
