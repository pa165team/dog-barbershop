package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.EmployeeCreateDTO;
import cz.muni.fi.pa165.dto.EmployeeDTO;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */

@Service
@Transactional
public class EmployeeFacadeImpl implements EmployeeFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeFacadeImpl(EmployeeService employeeService, BeanMappingService beanMappingService) {
        this.employeeService = employeeService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return beanMappingService.mapTo(employeeService.findAll(), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeService.findById(id);
        return (employee == null) ? null : beanMappingService.mapTo(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesBelowSalary(BigDecimal salary) {
        return beanMappingService.mapTo(employeeService.findAllBelowSalary(salary), EmployeeDTO.class);
    }

    @Override
    public Long createEmployee(EmployeeCreateDTO employee) {
        Employee newEmployee = new Employee();
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setName(employee.getName());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setSurname(employee.getSurname());
        employeeService.create(newEmployee);
        return newEmployee.getId();
    }

    @Override
    public void removeEmployee(EmployeeDTO employee) {
        employeeService.remove(employeeService.findById(employee.getId()));
    }

    @Override
    public Long updateEmployee(EmployeeDTO employee) {
        Employee updatedEmployee = employeeService.findById(employee.getId());
        updatedEmployee.setAddress(employee.getAddress());
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setPhoneNumber(employee.getPhoneNumber());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setSurname(employee.getSurname());
        return employee.getId();
    }
}
