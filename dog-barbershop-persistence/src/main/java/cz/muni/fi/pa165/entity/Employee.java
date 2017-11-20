package cz.muni.fi.pa165.entity;


import cz.muni.fi.pa165.utils.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "employee")
    private Set<ServiceRecord> serviceRecords = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(Set<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    public Employee() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getPhoneNumber(), employee.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
