package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.ServiceRecord;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Repository
public class ServiceRecordDaoImpl implements ServiceRecordDao {

    @PersistenceContext
    private EntityManager em;

    public ServiceRecord findById(Long id) {
        return em.find(ServiceRecord.class, id);
    }

    public void create(ServiceRecord serviceRecord) {
        em.persist(serviceRecord);
    }

    public void delete(ServiceRecord serviceRecord) {
        em.remove(serviceRecord);
    }

    public List<ServiceRecord> findAll() {
        return em.createQuery("SELECT s FROM ServiceRecord s", ServiceRecord.class)
            .getResultList();
    }

    @Override
    public List<ServiceRecord> getRecordsByDog(Dog dog) {
        return em.
            createQuery("SELECT s FROM ServiceRecord s WHERE s.dog = :dog", ServiceRecord.class)
            .setParameter("dog", dog)
            .getResultList();
    }

    public List<ServiceRecord> getServicesProvidedBetween(Date start, Date end) {
        return em
            .createQuery("SELECT s FROM ServiceRecord s WHERE s.dateProvided BETWEEN :startDate AND :endDate", ServiceRecord.class)
            .setParameter("startDate", start)
            .setParameter("endDate", end)
            .getResultList();
    }

    @Override
    public void update(ServiceRecord serviceRecord) {
        em.merge(serviceRecord);
    }
}
