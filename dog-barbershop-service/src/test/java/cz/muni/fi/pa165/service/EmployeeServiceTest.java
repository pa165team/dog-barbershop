package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.EmployeeDao;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import java.math.BigDecimal;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.mockito.Mockito.verify;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class EmployeeServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    
    private Employee employee;
    
    @Mock
    private EmployeeDao employeeDao;

    @Autowired
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void makeEmployee(){
        employee = new Employee();
        employee.setName("Michal");
        employee.setSurname("Michalek");
        employee.setPhoneNumber("1234567890");
        employee.setAddress(new Address("Michalovce", "Michalskeho", 18));
        employee.setSalary(new BigDecimal("1337"));
    }

    @Test
    public void findEmployeeWithId(){
        Long id = 18L;

        employeeService.findById(id);

        verify(employeeDao).findById(id);
    }

    @Test
    public void findAllEmployees(){
        employeeService.findAll();

        verify(employeeDao).findAll();
    }

    @Test
    public void createEmployee(){
        employeeService.create(employee);

        verify(employeeDao).create(employee);
    }

    @Test
    public void removeExistingEmployee(){
        employeeService.remove(employee);

        verify(employeeDao).delete(employee);
    }

    @Test
    public void findAllEmployeesBelowSalary(){
        BigDecimal salary = new BigDecimal("1500");

        employeeService.findAllBelowSalary(salary);

        verify(employeeDao).findAllBelowSalary(salary);
    }

    @Test
    public void updateEmployee(){
        employeeService.update(employee);

        verify(employeeDao).update(employee);
    }
}
