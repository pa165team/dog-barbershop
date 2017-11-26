package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Employee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
@Service
public interface EmployeeService {
    Employee findById(Long id);

    List<Employee> findAll();

    void create(Employee employee);

    void remove(Employee employee);

    List<Employee> findAllBelowSalary(BigDecimal salary);

    void update(Employee employee);
}
