package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.ServiceType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jan Kalfus
 */
@Repository
public class ServiceTypeDaoImpl implements ServiceTypeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ServiceType> findAll() {
        return em.createQuery("SELECT s FROM ServiceType s", ServiceType.class)
            .getResultList();
    }

    @Override
    public ServiceType findById(Long id) {
        return em.find(ServiceType.class, id);
    }

    @Override
    public void update(ServiceType type) {
        em.merge(type);
    }

    @Override
    public void create(ServiceType type) {
        em.persist(type);
    }

    @Override
    public void delete(ServiceType type) {
        em.remove(type);
    }
}
