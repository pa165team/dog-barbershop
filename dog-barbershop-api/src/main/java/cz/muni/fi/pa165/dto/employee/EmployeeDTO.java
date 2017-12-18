package cz.muni.fi.pa165.dto.employee;

import cz.muni.fi.pa165.ValidationMessages;
import cz.muni.fi.pa165.dto.servicerecord.ServiceRecordDTO;
import cz.muni.fi.pa165.utils.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Kalfus
 */
public class EmployeeDTO {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30, message = ValidationMessages.NAME)
    private String name;

    @NotNull
    @Size(min = 2, max = 30, message = ValidationMessages.SURNAME)
    private String surname;

    @NotNull(message = ValidationMessages.ADDRESS)
    private Address address;

    @NotNull(message = ValidationMessages.SALARY)
    private BigDecimal salary;

    @NotNull
    @Size(min = 7, max = 13, message = ValidationMessages.PHONE)
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
