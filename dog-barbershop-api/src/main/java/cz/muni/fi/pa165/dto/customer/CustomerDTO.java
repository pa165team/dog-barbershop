package cz.muni.fi.pa165.dto.customer;


import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.utils.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * @author Lucie Kolarikova
 */

public class CustomerDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 30, message = "Please enter a name at least 2 characters long")
    private String name;

    @NotNull
    @Size(min = 2, max = 30, message = "Please enter a surname at least 2 cahracters long")
    private String surname;

    @NotNull(message = "Please enter an address in format [Street] [House No.], [City]")
    private Address address;

    @NotNull
    @Size(min = 7, max = 13, message = "Please enter a phone number")
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
