package cz.muni.fi.pa165.dto.customer;


import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.utils.Address;

import java.util.List;
import java.util.Objects;

/**
 * @author Lucie Kolarikova
 */

public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private Address address;
    private String phoneNumber;
    private List<DogDTO> dogs;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public List<DogDTO> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogDTO> dogs) {
        this.dogs = dogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDTO that = (CustomerDTO) o;

        return Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber());
    }
}
