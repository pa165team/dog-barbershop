package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Mudrik (433655)
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public Employee findById(Long id) {
        return em.find(Employee.class,id);
    }
    
    public void create(Employee employee) {
        em.persist(employee);
    }
    
    public void delete(Employee employee) {
        em.remove(employee);
    }
    
    public List<Employee> findAll() {
        return em.createQuery("SELECT emp FROM Employee emp", Employee.class)
                .getResultList();
    }    
    
    public List<Employee> findAllBelowSalary(BigDecimal salary) {
         try {
            return em.createQuery("SELECT emp FROM Employee emp WHERE emp.salary < :salary", Employee.class)
                    .setParameter("salary", salary)
                    .getResultList();
         } catch (NoResultException ex) {
             return null;
         }
    }
}
