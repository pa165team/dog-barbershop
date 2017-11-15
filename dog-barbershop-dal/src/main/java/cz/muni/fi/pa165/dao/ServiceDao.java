package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
public interface ServiceDao {
    Service findById(Long id);

    void create(Service service);
    
    void update(Service service);

    void delete(Service service);

    List<Service> findAll();

    /**
     * Gets services based on their description
     *
     * @param description Description
     * @return All services containing the description string, or null if no match is found.
     */
    List<Service> getAllMatchingDescription(String description);

    /**
     * Gets services that were provided between two dates
     *
     * @param start First date
     * @param end   Second date
     * @return All matching services, or null if no services match.
     */
    List<Service> getServicesProvidedBetween(Date start, Date end);
}
