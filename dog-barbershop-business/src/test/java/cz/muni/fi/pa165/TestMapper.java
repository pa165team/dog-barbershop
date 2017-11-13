package cz.muni.fi.pa165;


import cz.muni.fi.pa165.dto.DogDTO;
import cz.muni.fi.pa165.entity.Customer;
import cz.muni.fi.pa165.entity.Dog;
import cz.muni.fi.pa165.entity.Employee;
import cz.muni.fi.pa165.entity.Service;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.config.MappingServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;

@ContextConfiguration(classes = MappingServiceConfiguration.class)
public class TestMapper extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private Dog dog;
    private DogDTO dogDTO;
    private Customer customer;
    private Employee employee;
    private Service service;

    @BeforeClass
    public void createEntities(){
        this.dog = new Dog("Rex", "Golden Retriever", new Date(2017, 11, 11), Gender.MALE, null, null);
        this.dogDTO = new DogDTO("Cody", "Golden Retriever", new Date(2016,1,22), Gender.MALE);
        this.customer = new Customer();
        this.employee = new Employee();
        this.service = new Service();
        //TODO: fill entities with data to compare
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

}
