package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Service;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Repository
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    private EntityManager em;

    public Service findById(Long id) {
        return em.find(Service.class, id);
    }

    public void create(Service service) {
        em.persist(service);
    }

    public void delete(Service service) {
        em.remove(service);
    }

    public List<Service> findAll() {
        return em.createQuery("SELECT s FROM Service s", Service.class)
            .getResultList();
    }

    public List<Service> getAllMatchingDescription(String description) {
        try {
            return em.createQuery("SELECT s FROM Service s WHERE s.description LIKE :description", Service.class)
                .setParameter("description", "%" + description + "%")
                .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Service> getServicesProvidedBetween(Date start, Date end) {
        try {
            return em
                .createQuery("SELECT s FROM Service s WHERE s.dateProvided BETWEEN :startDate AND :endDate", Service.class)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
