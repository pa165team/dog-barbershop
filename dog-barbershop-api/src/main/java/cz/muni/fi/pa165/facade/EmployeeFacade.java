package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.employee.EmployeeCreateDTO;
import cz.muni.fi.pa165.dto.employee.EmployeeDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
public interface EmployeeFacade {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployeesBelowSalary(BigDecimal salary);

    Long createEmployee(EmployeeCreateDTO employee);

    void removeEmployee(EmployeeDTO employee);

    Long updateEmployee(EmployeeDTO employee);
}
