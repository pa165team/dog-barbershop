package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dao.EmployeeDao;
import cz.muni.fi.pa165.dto.EmployeeCreateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.EmployeeServiceImpl;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestEmployeeFacade extends AbstractTransactionalTestNGSpringContextTests {

    private Employee jozkoMrkvicka;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmployeeDao employeeDao;

    private EmployeeService employeeService;
    private EmployeeFacadeImpl fac;

    @Autowired
    private BeanMappingService beanMappingService;

    @BeforeMethod
    public void setupEvery() {
        employeeService = new EmployeeServiceImpl(employeeDao);
        jozkoMrkvicka = new Employee();
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

        employeeService.create(jozkoMrkvicka);
        employeeService.create(jozefParadajka);
        employeeService.create(jozinoMrkvicka);
        fac = new EmployeeFacadeImpl(employeeService, beanMappingService);
    }

    @Test
    public void getAllEmployees() {
        List<EmployeeDTO> employees = fac.getAllEmployees();
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void getEmployeeById() {
        Assert.assertEquals(jozkoMrkvicka.getPhoneNumber(), fac.getEmployeeById(jozkoMrkvicka.getId()).getPhoneNumber());
    }

    @Test
    public void getAllEmployeesBelowSalary() {
        Assert.assertEquals(3, fac.getAllEmployeesBelowSalary(new BigDecimal("1500")).size());
        Assert.assertEquals(2, fac.getAllEmployeesBelowSalary(new BigDecimal("1100")).size());
    }

    @Test
    public void createEmployee() {
        EmployeeCreateDTO jozo = new EmployeeCreateDTO();
        jozo.setName("Jozo");
        jozo.setSurname("Mrkvo");
        jozo.setPhoneNumber("1234567890");
        jozo.setAddress(new Address("Samorino", "Tejfaluo", 90));
        jozo.setSalary(new BigDecimal("10000"));
        Long unused = fac.createEmployee(jozo);
        Assert.assertEquals(4, fac.getAllEmployees().size());
    }

    @Test
    public void removeEmployee() {
        EmployeeDTO mrkvicka = fac.getEmployeeById(jozkoMrkvicka.getId());
        fac.removeEmployee(mrkvicka);
        em.flush();
        Assert.assertEquals(2, fac.getAllEmployees().size());
    }

    @Test
    public void updateEmployee() {
        EmployeeDTO mrkvicka = fac.getEmployeeById(jozkoMrkvicka.getId());
        mrkvicka.setName("Michal");
        Long unused = fac.updateEmployee(mrkvicka);
        Assert.assertEquals("Michal", fac.getEmployeeById(jozkoMrkvicka.getId()).getName());
    }
}
