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
    public Employee findById(Long id);

    /**
     * Persist employee in DB
     *
     * @param employee
     */
    public void create(Employee employee);

    /**
     * Delete employee from DB
     *
     * @param employee
     */
    public void delete(Employee employee);

    /**
     * Find all employees in DB
     *
     * @return All employees
     */
    public List<Employee> findAll();

    /**
     * Find employees with salary under specified value
     *
     * @param salary
     * @return Employees with salary below 'salary'
     */
    public List<Employee> findAllBelowSalary(BigDecimal salary);
}
