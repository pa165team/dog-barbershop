package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.ServiceType;

import java.util.List;

/**
 * @author Jan Kalfus
 */
public interface ServiceTypeDao {
    void create(ServiceType serviceType);

    List<ServiceType> findAll();

    ServiceType findById(Long id);

    void update(ServiceType type);

    void delete(ServiceType type);
}
