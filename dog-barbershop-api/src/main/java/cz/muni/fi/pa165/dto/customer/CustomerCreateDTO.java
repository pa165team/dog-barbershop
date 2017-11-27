package cz.muni.fi.pa165.dto.customer;

import cz.muni.fi.pa165.utils.Address;

import javax.validation.constraints.NotNull;

/**
 * @author Lucie Kolarikova
 */

public class CustomerCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private Address address;

    @NotNull
    private String phoneNumber;

    public CustomerCreateDTO(@NotNull String name, @NotNull String surname, @NotNull Address address, @NotNull String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerCreateDTO{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", address=" + address +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCreateDTO that = (CustomerCreateDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(that.getSurname()) : that.getSurname() != null) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }
}
