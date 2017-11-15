package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.DogCreateDTO;
import cz.muni.fi.pa165.dto.DogDTO;
import cz.muni.fi.pa165.enums.Gender;

import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

public interface DogFacade {
    List<DogDTO> getAllDogs();
    DogDTO getDogById(Long id);
    List<DogDTO> getAllDogsOfGender(Gender gender);
    Long createDog(DogCreateDTO newDog);
    void removeDog(DogDTO dog);
    Long updateDog(DogDTO dog);
}
