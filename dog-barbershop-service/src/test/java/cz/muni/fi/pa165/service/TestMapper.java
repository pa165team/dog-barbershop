package cz.muni.fi.pa165.service;


import cz.muni.fi.pa165.dto.dog.DogDTO;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestMapper extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private Dog dog;
    private DogDTO dogDTO;
    private List<Dog> dogs;
    private List<DogDTO> dogDTOs;

    @BeforeClass
    public void createEntities(){
        this.dog = new Dog("Rex", "Golden Retriever", new Date(2017, 11, 11), Gender.MALE, null);
        this.dogDTO = new DogDTO("Cody", "Golden Retriever", new Date(2016,1,22), Gender.MALE);
        this.dogs = this.initializeDogs();
        this.dogDTOs = this.initializeDogDTOs();
    }

    private List<Dog> initializeDogs(){
        Dog dog1 = new Dog("Rexo", "German Shepherd", new Date(2017, 11, 15), Gender.MALE, null);
        Dog dog2 = new Dog("Rexi", "German Shepherd", new Date(2017, 11, 15), Gender.FEMALE, null);
        Dog dog3 = new Dog("Franklin", "Chihuahua", new Date(2017, 11, 15), Gender.MALE, null);
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);
        dogs.add(dog3);
        return dogs;
    }

    private List<DogDTO> initializeDogDTOs(){
        DogDTO dog1 = new DogDTO("Cody", "Golden Retriever", new Date(2016,1,22), Gender.MALE);
        DogDTO dog2 = new DogDTO("Boby", "GodKnows", new Date(1996,4,4), Gender.MALE);
        DogDTO dog3 = new DogDTO("Connor", "Border Collie", new Date(2015,11,23), Gender.MALE);
        List<DogDTO> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);
        dogs.add(dog3);
        return dogs;
    }

    @Test
    public void dogIsMappedToDogDTO(){
        DogDTO dogDTO = beanMappingService.mapTo(this.dog, DogDTO.class);
        Assert.assertEquals(this.dog.getName(), dogDTO.getName());
        Assert.assertEquals(this.dog.getGender(), dogDTO.getGender());
    }

    @Test
    public void dogDTOisMappedToDog(){
        Dog dog = beanMappingService.mapTo(this.dogDTO, Dog.class);
        Assert.assertEquals(this.dogDTO.getName(), dog.getName());
        Assert.assertEquals(this.dogDTO.getGender(), dog.getGender());
    }

    @Test
    public void listOfDogsIsMappedToListOfDogDTOs(){
        List<DogDTO> dogDTOs = beanMappingService.mapTo(this.dogs, DogDTO.class);
        Assert.assertEquals(3, dogDTOs.size());
        Assert.assertEquals("Franklin", dogDTOs.get(2).getName());
    }

    @Test
    public void listOfDogDTOsIsMappedToListOfDogs(){
        List<Dog> dogs = beanMappingService.mapTo(this.dogDTOs, Dog.class);
        Assert.assertEquals(3, dogs.size());
        Assert.assertEquals("Boby", dogs.get(1).getName());
    }

}
