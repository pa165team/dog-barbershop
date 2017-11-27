package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeCreateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.EmployeeService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Daniel Mudrik (433655)
 */
@ContextConfiguration(classes = {MappingServiceConfiguration.class})
public class TestEmployeeFacade extends AbstractTransactionalTestNGSpringContextTests {

    private EmployeeService employeeServiceMock;
    private Employee employeeMock;
    private BeanMappingService beanServiceMock;
    private EmployeeFacadeImpl facade;
    private static final Long ID = 42L;

    @BeforeMethod
    public void setupEvery() {
        employeeServiceMock = mock(EmployeeService.class);
        employeeMock = mock(Employee.class);
        beanServiceMock = mock(BeanMappingService.class);
        facade = new EmployeeFacadeImpl(employeeServiceMock, beanServiceMock);
    }

    @Test
    public void getAllEmployees() {
        List<Employee> employeeMockList = new ArrayList<>();
        employeeMockList.add(employeeMock);
        List<EmployeeDTO> employeeDTOMockList = new ArrayList<>();
        when(employeeServiceMock.findAll()).thenReturn(employeeMockList);
        when(beanServiceMock.mapTo(employeeMockList, EmployeeDTO.class)).thenReturn(employeeDTOMockList);

        facade.getAllEmployees();
        
        verify(employeeServiceMock).findAll();
        verify(beanServiceMock).mapTo(employeeMockList, EmployeeDTO.class);
    }

    @Test
    public void getEmployeeById() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(ID);
        when(employeeServiceMock.findById(ID)).thenReturn(employeeMock);
        when(beanServiceMock.mapTo(employeeMock, EmployeeDTO.class)).thenReturn(employeeDTO);
        
        EmployeeDTO verified = facade.getEmployeeById(ID);
        Assert.assertEquals(ID, verified.getId());
        verify(employeeServiceMock).findById(ID);
        verify(beanServiceMock).mapTo(employeeMock, EmployeeDTO.class);
    }

    @Test
    public void getAllEmployeesBelowSalary() {
        BigDecimal salary = new BigDecimal("1500");
        facade.getAllEmployeesBelowSalary(salary);
        verify(employeeServiceMock).findAllBelowSalary(salary);
    }

    @Test
    public void createEmployee() {
        EmployeeCreateDTO jozo = new EmployeeCreateDTO();
        Employee emp = new Employee();
        jozo.setName("Jozo");
        jozo.setSurname("Mrkvo");
        jozo.setPhoneNumber("1234567890");
        jozo.setAddress(new Address("Samorino", "Tejfaluo", 90));
        jozo.setSalary(new BigDecimal("10000"));
        facade.createEmployee(jozo);
        verify(employeeServiceMock).create(any());
    }
    
    @Test
    public void removeEmployee() {
        EmployeeDTO emp = new EmployeeDTO();
        emp.setId(ID);
        when(employeeServiceMock.findById(ID)).thenReturn(employeeMock);
        facade.removeEmployee(emp);
        verify(employeeServiceMock).findById(ID);
        verify(employeeServiceMock).remove(any());
    }
    
    @Test
    public void updateEmployee() {
        EmployeeDTO emp = new EmployeeDTO();
        emp.setId(ID);
        when(employeeServiceMock.findById(ID)).thenReturn(employeeMock);
        facade.updateEmployee(emp);
        verify(employeeServiceMock).findById(ID);
    }
}