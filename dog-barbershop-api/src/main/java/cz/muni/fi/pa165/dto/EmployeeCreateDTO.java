package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.utils.Address;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Daniel Mudrik (433655)
 */
public class EmployeeCreateDTO {
    @NotNull
    @Length(min = 2, max = 30)
    private String name;

    @NotNull
    @Length(min = 2, max = 30)
    private String surname;

    @NotNull
    private Address address;

    @NotNull
    private BigDecimal salary;

    @NotNull
    @Length(min = 7, max = 13)
    private String phoneNumber;

    public EmployeeCreateDTO() {
    }

    public EmployeeCreateDTO(String name, String surname, Address address, BigDecimal salary, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "EmployeeCreateDTO{" +
            "name=" + name +
            ", surname=" + surname +
            ", address=" + address +
            ", salary=" + salary +
            ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.surname);
        hash = 13 * hash + Objects.hashCode(this.address);
        hash = 13 * hash + Objects.hashCode(this.salary);
        hash = 13 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(getClass() != obj.getClass())) {
            return false;
        }
        final EmployeeCreateDTO other = (EmployeeCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.salary, other.salary);
    }


}
