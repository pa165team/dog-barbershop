package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.dog.DogCreateDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CustomerService;
import cz.muni.fi.pa165.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * @author Martin Kuchar 433499
 */

@Service
@Transactional
public class DogFacadeImpl implements DogFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private DogService dogService;

    @Autowired
    private CustomerService customerService;

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
        Dog dog = new Dog(
            newDog.getName(),
            newDog.getBreed(),
            newDog.getDateOfBirth(),
            newDog.getGender(),
            customerService.findById(newDog.getOwnerId()));
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

    @Override
    public Long drawLuckyDogToHaveDiscount() {
        Dog luckyDog = dogService.getRandomDog();
        luckyDog.setHasDiscount(true);
        dogService.update(luckyDog);
        return luckyDog.getId();
    }


}
