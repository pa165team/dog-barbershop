package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import cz.muni.fi.pa165.utils.Address;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class EmployeeDTO {
    private Long id;
    private String name;
    private String surname;
    private Address address;
    private BigDecimal salary;
    private String phoneNumber;
    private List<ServiceRecordDTO> serviceRecords;

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

    public List<ServiceRecordDTO> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecordDTO> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
