package cz.muni.fi.pa165.dto.dog;

import cz.muni.fi.pa165.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Martin Kuchar 433499
 */

public class DogCreateDTO {

    @NotNull
    @Length(min = 2, max = 30)
    private String name;

    @NotNull
    @Length(min = 1, max = 30)
    private String breed;

    @NotNull
    private Date dateOfBirth;

    @Enumerated
    @NotNull
    private Gender gender;

    @NotNull
    private Long ownerId;

    public DogCreateDTO() {
    }

    public DogCreateDTO(String name, String breed, Date dateOfBirth, Gender gender, Long ownerId) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.ownerId = ownerId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString(){
        return "DogCreateDTO[name: "+this.getName()+
            ", breed: "+this.getBreed()+
            ", dateOfBirth: "+this.getDateOfBirth()+
            ", gender: "+this.getGender()+"];";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DogCreateDTO that = (DogCreateDTO) o;

        if (!getName().equals(that.getName())) return false;
        if (!getBreed().equals(that.getBreed())) return false;
        if (!getDateOfBirth().equals(that.getDateOfBirth())) return false;
        return getGender() == that.getGender();
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
