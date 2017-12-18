package cz.muni.fi.pa165.dto.dog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.muni.fi.pa165.dto.customer.CustomerDTO;
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

    private Boolean hasDiscount;

    @JsonIgnore
    private CustomerDTO owner;

    public DogDTO() {
    }

    public DogDTO(String name, String breed, Date dateOfBirth, Gender gender, Boolean hasDiscount, CustomerDTO owner) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.hasDiscount = hasDiscount;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public CustomerDTO getOwner() {
        return owner;
    }

    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogDTO)) return false;

        DogDTO dogDTO = (DogDTO) o;

        if (getId() != null ? !getId().equals(dogDTO.getId()) : dogDTO.getId() != null) return false;
        if (getName() != null ? !getName().equals(dogDTO.getName()) : dogDTO.getName() != null) return false;
        if (getBreed() != null ? !getBreed().equals(dogDTO.getBreed()) : dogDTO.getBreed() != null) return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(dogDTO.getDateOfBirth()) : dogDTO.getDateOfBirth() != null) {
            return false;
        }
        if (getGender() != dogDTO.getGender()) return false;
        if (getHasDiscount() != null ? !getHasDiscount().equals(dogDTO.getHasDiscount()) : dogDTO.getHasDiscount() != null) {
            return false;
        }
        return getOwner() != null ? getOwner().equals(dogDTO.getOwner()) : dogDTO.getOwner() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBreed() != null ? getBreed().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getHasDiscount() != null ? getHasDiscount().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        return result;
    }
}
