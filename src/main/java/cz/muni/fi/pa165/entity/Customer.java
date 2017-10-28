package cz.muni.fi.pa165.entity;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;
import cz.muni.fi.pa165.utils.Address;

import java.util.Set;

/**
 * Class for customer of the Dog barbershop
 * @author Lucie Kolarikova
 */
@Entity
public class Customer {

    @Id
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
    private String phoneNumber;

    //TODO: Martin here, this might be what you want to add for association with dog. Finish pls :)
    /*@NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "owner")
    private Set<Dog> dogs;*/

    public Customer(){
    }

    /**
     * Equals
     * @param o Given object
     * @return True if this and given object are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) o;

        if (!getName().equals(customer.getName())) return false;
        if (!getSurname().equals(customer.getSurname())) return false;
        if (!getAddress().equals(customer.getAddress())) return false;
        return getPhoneNumber().equals(customer.getPhoneNumber());
    }

    /**
     * Hashcode
     * @return Hashcode as integer
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    // Getters and setters
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
}
