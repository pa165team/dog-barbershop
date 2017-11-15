package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.DogCreateDTO;
import cz.muni.fi.pa165.dto.DogDTO;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

@Service
@Transactional
public class DogFacadeImpl implements DogFacade{

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private DogService dogService;

    @Override
    public List<DogDTO> getAllDogs() {
        return beanMappingService.mapTo(dogService.findAll(), DogDTO.class);
    }

    @Override
    public DogDTO getDogById(Long id) {
        return beanMappingService.mapTo(dogService.findById(id), DogDTO.class);
    }

    @Override
    public List<DogDTO> getAllDogsOfGender(Gender gender) {
        return beanMappingService.mapTo(dogService.findAllOfGender(gender), DogDTO.class);
    }

    @Override
    public Long createDog(DogCreateDTO newDog) {
        Dog dog = new Dog();
        dog.setName(newDog.getName());
        dog.setBreed(newDog.getBreed());
        dog.setDateOfBirth(newDog.getDateOfBirth());
        dog.setGender(newDog.getGender());
        dogService.create(dog);
        return dog.getId();
    }

    @Override
    public void removeDog(DogDTO dog) {
        dogService.remove(beanMappingService.mapTo(dog, Dog.class));
    }

    @Override
    public Long updateDog(DogDTO dog) {
        dogService.update(beanMappingService.mapTo(dog, Dog.class));
        return dog.getId();
    }
}
