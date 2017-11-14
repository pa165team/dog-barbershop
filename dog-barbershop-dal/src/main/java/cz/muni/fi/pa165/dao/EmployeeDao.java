package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
public interface EmployeeDao {

    /**
     * Find employee based on ID
     *
     * @param id
     * @return Employee with ID 'id'
     */
    Employee findById(Long id);

    /**
     * Persist employee in DB
     *
     * @param employee
     */
    void create(Employee employee);
    
    /**
     * Update employee in DB
     * @param employee
     */
    void update(Employee employee);

    /**
     * Delete employee from DB
     *
     * @param employee
     */
    void delete(Employee employee);

    /**
     * Find all employees in DB
     *
     * @return All employees
     */
    List<Employee> findAll();

    /**
     * Find employees with salary under specified value
     *
     * @param salary
     * @return Employees with salary below 'salary'
     */
    List<Employee> findAllBelowSalary(BigDecimal salary);
}
