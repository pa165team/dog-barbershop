package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.EmployeeDao;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Martin Kuchár 433499
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EmployeeDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmployeeDao employeeDao;

    @PersistenceContext
    private EntityManager em;
    
    private void createInitialEmployees(){
        addThreeEmployees();
    }

    private void addThreeEmployees() {
        Employee jozkoMrkvicka = new Employee();
        jozkoMrkvicka.setName("Jozko");
        jozkoMrkvicka.setSurname("Mrkvicka");
        jozkoMrkvicka.setPhoneNumber("123456789");
        jozkoMrkvicka.setAddress(new Address("Samorin", "Tejfalu", 9));
        jozkoMrkvicka.setSalary(new BigDecimal("1000"));

        Employee jozefParadajka = new Employee();
        jozefParadajka.setName("Jozef");
        jozefParadajka.setSurname("Paradajka");
        jozefParadajka.setPhoneNumber("192837465");
        jozefParadajka.setAddress(new Address("Luzna", "Tehelna", 5));
        jozefParadajka.setSalary(new BigDecimal("1000"));

        Employee jozinoMrkvicka = new Employee();
        jozinoMrkvicka.setName("Jozino");
        jozinoMrkvicka.setSurname("Mrkvicka");
        jozinoMrkvicka.setPhoneNumber("987654321");
        jozinoMrkvicka.setAddress(new Address("Somoria", "Mliecnanska", 1));
        jozinoMrkvicka.setSalary(new BigDecimal("1200"));

        employeeDao.create(jozkoMrkvicka);
        employeeDao.create(jozefParadajka);
        employeeDao.create(jozinoMrkvicka);
    }

    private Employee createRandomEmployee(){
        Employee employee = new Employee();
        employee.setName("Random");
        employee.setSurname("Employee");
        employee.setPhoneNumber("123456789");
        employee.setAddress(new Address("Brno", "Botanicka", 68));
        employee.setSalary(new BigDecimal("900"));

        employeeDao.create(employee);
        return employee;
    }

    @Test
    public void storesAndReceivesSingleEmployee() {
        Employee employee = createRandomEmployee();

        List<Employee> employees = employeeDao.findAll();
        Assert.assertEquals(employees.size(), 1);

        Employee employeeFromDao = employees.get(0);
        Assert.assertTrue(employee.equals(employeeFromDao));
    }

    @Test
    public void storesAndDeletesSingleEmployee() {
        Employee employee = createRandomEmployee();

        List<Employee> employees = employeeDao.findAll();
        Assert.assertEquals(employees.size(), 1);

        employeeDao.delete(employee);

        List<Employee> employees2 = employeeDao.findAll();
        Assert.assertEquals(employees2.size(), 0);
    }

    @Test
    public void findsEmployeeById() {
        Employee employee = createRandomEmployee();

        Employee foundEmployee = employeeDao.findById(employee.getId());
        Assert.assertTrue(employee.equals(foundEmployee));
    }

    @Test
    public void doesntFindNonExistentEmployee() {
        createRandomEmployee();

        Employee foundEmployee = employeeDao.findById(-1L);
        Assert.assertTrue(foundEmployee == null);
    }

    @Test
    public void findAllBelowCertainSalary(){
        createInitialEmployees();

        List<Employee> foundEmployees = employeeDao.findAllBelowSalary(new BigDecimal("1100"));
        Assert.assertEquals(foundEmployees.size(), 2);
    }

    @Test
    public void doesntFindNoEmployeeBelowMinimumSalary(){
        createInitialEmployees();

        List<Employee> foundEmployees = employeeDao.findAllBelowSalary(new BigDecimal("1000"));
        Assert.assertEquals(foundEmployees.size(), 0);
    }
    
    @Test
    public void updateEmployeeSalary() {
        Employee employee = createRandomEmployee();
        em.detach(employee);
        employee.setSalary(new BigDecimal("50"));
        employeeDao.update(employee);
        List<Employee> foundEmployees = employeeDao.findAllBelowSalary(new BigDecimal("60"));
        Assert.assertEquals(foundEmployees.size(), 1);
    }
}
