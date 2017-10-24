package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Dog {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Length(min = 2, max = 30)
    private String Name;

    @NotNull
    @Length(min = 1, max = 30)
    private String Breed;

    @NotNull
    private int Age;

    @NotNull
    private Gender Gender;

    public Dog(){}

    public Dog(String name, String breed, int age, Gender gender) {
        Name = name;
        Breed = breed;
        Age = age;
        Gender = gender;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getBreed() {
        return Breed;
    }

    public int getAge() {
        return Age;
    }

    public Gender getGender() {
        return Gender;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setGender(Gender gender) {
        Gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        return
            getAge() == dog.getAge() &&
            getId().equals(dog.getId()) &&
            getName().equals(dog.getName()) &&
            getName().equals(dog.getName()) &&
            getBreed().equals(dog.getBreed()) &&
            getGender() == dog.getGender();
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + getGender().hashCode();
        return result;
    }
}
