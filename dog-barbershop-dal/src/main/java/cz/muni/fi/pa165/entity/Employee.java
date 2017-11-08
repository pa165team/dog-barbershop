package cz.muni.fi.pa165.entity;


import cz.muni.fi.pa165.utils.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Employee entity class
 *
 * @author Daniel Mudrik (433655)
 */
@Entity
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String surname;

    @NotNull
    @Column(nullable = false)
    private Address address;

    @NotNull
    @Column(nullable = false)
    private BigDecimal salary;

    @NotNull
    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "employee")
    private Set<Service> services;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Employee() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Employee)) {
            return false;
        }
        Employee emp = (Employee) o;
        return getName().equals(emp.getName()) &&
            getSurname().equals(emp.getSurname()) &&
            getAddress().equals(emp.getAddress()) &&
            getSalary().equals(emp.getSalary()) &&
            getPhoneNumber().equals(emp.getPhoneNumber());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getSalary().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }
}
