package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

/**
 * @author Martin Kuchár 433499
 */

@Entity
public class Dog {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 30)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Length(min = 1, max = 30)
    @Column(nullable = false)
    private String breed;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "dog")
    private Set<Service> services;

    @NotNull
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Dog(){}

    public Dog(String name, String breed, Date dateOfBirth, Gender gender, Set<Service> services, Customer owner) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.services = services;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Service> getServices() { return services; }

    public void setServices(Set<Service> services) { this.services = services; }

    public Customer getOwner(){ return owner; }

    public void setOwner(Customer owner){ this.owner = owner; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Dog)) return false;

        Dog dog = (Dog) o;

        if (!getName().equals(dog.getName())) return false;
        if (!getBreed().equals(dog.getBreed())) return false;
        if (!getDateOfBirth().equals(dog.getDateOfBirth())) return false;
        if (getGender() != dog.getGender()) return false;
        if (!getServices().equals(dog.getServices())) return false;
        return getOwner().equals(dog.getOwner());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getServices().hashCode();
        result = 31 * result + getOwner().hashCode();
        return result;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Dog)) return false;

        Dog dog = (Dog) o;

        return
            getDateOfBirth() == dog.getDateOfBirth() &&
            getName().equals(dog.getName()) &&
            getName().equals(dog.getName()) &&
            getBreed().equals(dog.getBreed()) &&
            getGender() == dog.getGender();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getGender().hashCode();
        return result;
    }*/
}
