package cz.muni.fi.pa165.dto.dog;

import cz.muni.fi.pa165.ValidationMessages;
import cz.muni.fi.pa165.enums.Gender;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Martin Kuchar 433499
 */

public class DogUpdateDTO {

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

    public DogUpdateDTO() {
    }

    public DogUpdateDTO(String name, String breed, Date dateOfBirth, Gender gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
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
        if (o == null || getClass() != o.getClass()) return false;
        DogUpdateDTO that = (DogUpdateDTO) o;
        return Objects.equals(getName(), that.getName()) &&
            Objects.equals(getBreed(), that.getBreed()) &&
            Objects.equals(getDateOfBirth(), that.getDateOfBirth()) &&
            getGender() == that.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBreed(), getDateOfBirth(), getGender());
    }
}
