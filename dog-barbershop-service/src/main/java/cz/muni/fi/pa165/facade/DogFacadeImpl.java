package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.dog.DogCreateDTO;
import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.CustomerService;
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
public class DogFacadeImpl implements DogFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private DogService dogService;

    @Autowired
    private CustomerService customerService;


    public DogFacadeImpl(BeanMappingService beanMappingService, DogService dogService, CustomerService customerService) {
        this.beanMappingService = beanMappingService;
        this.dogService = dogService;
        this.customerService = customerService;
    }

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
        Customer owner = customerService.findById(newDog.getOwnerId());
        Dog dog = new Dog(
            newDog.getName(),
            newDog.getBreed(),
            newDog.getDateOfBirth(),
            newDog.getGender(),
            owner
        );
        dog.setHasDiscount(false);
        owner.addDog(dog);
        dogService.create(dog);
        customerService.update(owner);
        return dog.getId();
    }

    @Override
    public void removeDog(DogDTO dog) {
        Customer owner = customerService.findById(dog.getOwner().getId());
        Dog originalDog = dogService.findById(dog.getId());
        owner.removeDog(originalDog);
        dogService.remove(originalDog);
        customerService.update(owner);
    }

    @Override
    public Long updateDog(DogDTO dog) {
        Dog dogToUpdate = dogService.findById(dog.getId());
        dogToUpdate.setOwner(customerService.findById(dog.getOwner().getId()));
        dogToUpdate.setHasDiscount(dog.getHasDiscount());
        dogToUpdate.setGender(dog.getGender());
        dogToUpdate.setDateOfBirth(dog.getDateOfBirth());
        dogToUpdate.setBreed(dog.getBreed());
        dogToUpdate.setName(dog.getName());
        return dog.getId();
    }

    @Override
    public DogDTO drawLuckyDogToHaveDiscount() {
        Dog luckyDog = dogService.drawRandomDog();
        luckyDog.setHasDiscount(true);
        dogService.update(luckyDog);
        return beanMappingService.mapTo(luckyDog, DogDTO.class);
    }


}
