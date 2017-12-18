package cz.muni.fi.pa165.dto.dog;

import cz.muni.fi.pa165.ValidationMessages;
import cz.muni.fi.pa165.enums.Gender;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * @author Martin Kuchar 433499
 */

public class DogCreateDTO {

    @NotNull
    @Size(min = 2, max = 30, message = ValidationMessages.NAME)
    private String name;

    @NotNull
    @Size(min = 1, max = 30, message = ValidationMessages.BREED)
    private String breed;

    @NotNull(message = ValidationMessages.DOB)
    private Date dateOfBirth;

    @Enumerated
    @NotNull(message = ValidationMessages.GENDER)
    private Gender gender;

    @NotNull(message = ValidationMessages.OWNER_SELECT)
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
        if (!(o instanceof DogCreateDTO)) return false;

        DogCreateDTO that = (DogCreateDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getBreed() != null ? !getBreed().equals(that.getBreed()) : that.getBreed() != null) return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(that.getDateOfBirth()) : that.getDateOfBirth() != null) {
            return false;
        }
        if (getGender() != that.getGender()) return false;
        return getOwnerId() != null ? getOwnerId().equals(that.getOwnerId()) : that.getOwnerId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getBreed() != null ? getBreed().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getOwnerId() != null ? getOwnerId().hashCode() : 0);
        return result;
    }
}
