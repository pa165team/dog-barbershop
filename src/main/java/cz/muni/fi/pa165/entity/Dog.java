package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private int age;

    @NotNull
    @Column(nullable = false)
    private Gender gender;

    //TODO: Add dependencies on other classes when they are ready!

    public Dog(){}

    public Dog(String name, String breed, int age, Gender gender) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
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

    public int getAge() {
        return age;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return
            getAge() == dog.getAge() &&
            getName().equals(dog.getName()) &&
            getName().equals(dog.getName()) &&
            getBreed().equals(dog.getBreed()) &&
            getGender() == dog.getGender();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + getGender().hashCode();
        return result;
    }
}
