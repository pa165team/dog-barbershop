package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Gender;
import java.sql.Date;

/**
 * @author Martin Kuchar 433499
 */

public class DogDTO {

    private Long id;

    private String name;

    private String breed;

    private Date dateOfBirth;

    private Gender gender;

    public DogDTO() {
    }

    public DogDTO(String name, String breed, Date dateOfBirth, Gender gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||  o.getClass() != this.getClass()) return false;

        DogDTO dog = (DogDTO) o;

        if (!getName().equals(dog.getName())) return false;
        if (!getBreed().equals(dog.getBreed())) return false;
        if (!getDateOfBirth().equals(dog.getDateOfBirth())) return false;
        return getGender() == dog.getGender();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getGender().hashCode();
        return result;
    }

}
