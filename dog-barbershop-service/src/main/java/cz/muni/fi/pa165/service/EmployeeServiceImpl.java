/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.EmployeeDao;
import cz.muni.fi.pa165.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Daniel Mudrik (433655)
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public void remove(Long employeeId) {

        employeeDao.delete(employeeDao.findById(employeeId));
    }

    @Override
    public List<Employee> findAllBelowSalary(BigDecimal salary) {
        return employeeDao.findAllBelowSalary(salary);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

}
