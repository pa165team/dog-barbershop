package cz.muni.fi.pa165.utils;

import javax.persistence.Embeddable;

/**
 * @author Lucie Kolarikova
 */
@Embeddable
public class Address {
    private String city;
    private String street;
    private int number;

    public Address(String city, String street, int number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getNumber() != address.getNumber()) return false;
        if (!getCity().equals(address.getCity())) return false;
        return getStreet().equals(address.getStreet());
    }

    @Override
    public int hashCode() {
        int result = getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getNumber();
        return result;
    }
}
