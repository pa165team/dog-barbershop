package cz.fi.muni.pa165.sampledata;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.enums.Gender;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.utils.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jan Kalfus
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private ServiceRecordService serviceRecordService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DogService dogService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void loadData() throws IOException {
        //load sample data for entities
        List<Customer> customers = this.loadCustomers();
        List<Dog> dogs = this.loadDogs();
        List<Employee> employees = this.loadEmployees();
        List<ServiceType> serviceTypes = this.loadServiceTypes();
        List<ServiceRecord> serviceRecords = this.loadServiceRecords();

        //create relationships between entities
        customers.get(0).addDog(dogs.get(0));
        dogs.get(0).setOwner(customers.get(0));

        customers.get(0).addDog(dogs.get(2));
        dogs.get(2).setOwner(customers.get(0));

        customers.get(1).addDog(dogs.get(1));
        dogs.get(1).setOwner(customers.get(1));

        Set<ServiceRecord> srForHonza = new HashSet<>();
        srForHonza.add(serviceRecords.get(0));
        srForHonza.add(serviceRecords.get(2));
        employees.get(0).setServiceRecords(srForHonza);

        Set<ServiceRecord> srForJulius = new HashSet<>();
        srForJulius.add(serviceRecords.get(1));
        employees.get(1).setServiceRecords(srForJulius);

        serviceRecords.get(0).setEmployee(employees.get(0));
        serviceRecords.get(0).setDog(dogs.get(0));

        serviceRecords.get(1).setEmployee(employees.get(1));
        serviceRecords.get(1).setDog(dogs.get(0));

        Set<ServiceRecord> srForDog0 = new HashSet<>();
        srForDog0.add(serviceRecords.get(0));
        srForDog0.add(serviceRecords.get(1));
        dogs.get(0).setServiceRecords(srForDog0);

        serviceRecords.get(2).setEmployee(employees.get(0));
        serviceRecords.get(2).setDog(dogs.get(2));

        Set<ServiceRecord> srForDog2 = new HashSet<>();
        srForDog2.add(serviceRecords.get(2));
        dogs.get(2).setServiceRecords(srForDog2);

        serviceRecords.get(0).setServiceType(serviceTypes.get(0));
        serviceRecords.get(1).setServiceType(serviceTypes.get(0));
        serviceRecords.get(2).setServiceType(serviceTypes.get(1));

        Set<ServiceRecord> srForst0 = new HashSet<>();
        srForst0.add(serviceRecords.get(0));
        srForst0.add(serviceRecords.get(1));
        serviceTypes.get(0).setServiceRecords(srForst0);

        Set<ServiceRecord> srForst1 = new HashSet<>();
        srForst1.add(serviceRecords.get(2));
        serviceTypes.get(1).setServiceRecords(srForst1);

        //create connected entities
        this.createCustomers(customers);
        this.createEmployees(employees);
        this.createDogs(dogs);
        this.createServiceTypes(serviceTypes);
        this.createServiceRecords(serviceRecords);
    }

    private List<Customer> loadCustomers(){
        List<Customer> customers = new ArrayList<>();

        Customer c1 = new Customer();
        c1.setName("Jozko");
        c1.setSurname("Mrkvicka");
        c1.setAddress(new Address("Kalinkovo", "Pri hradzi", 12));
        c1.setPhoneNumber("0944123456");
        customers.add(c1);

        Customer c2 = new Customer();
        c2.setName("Andrej");
        c2.setSurname("Kiska");
        c2.setAddress(new Address("Poprad", "Vysoka", 343));
        c2.setPhoneNumber("0905476916");
        customers.add(c2);

        return customers;
    }

    private void createCustomers(List<Customer> customers){
        for (Customer customer: customers) {
            this.createCustomer(customer);
        }
    }

    private void createCustomer(Customer customer){
        this.customerService.create(customer);
    }

    private List<Dog> loadDogs(){
        List<Dog> dogs = new ArrayList<>();

        Dog d1 = new Dog();
        d1.setName("Rexi");
        d1.setBreed("German Shepherd");
        d1.setDateOfBirth(new Date(2011, 12, 13));
        d1.setGender(Gender.FEMALE);
        dogs.add(d1);

        Dog d2 = new Dog();
        d2.setName("Cody");
        d2.setBreed("Golden Retriever");
        d2.setDateOfBirth(new Date(2016, 1, 22));
        d2.setGender(Gender.MALE);
        dogs.add(d2);

        Dog d3 = new Dog();
        d3.setName("Connor");
        d3.setBreed("Border Collie");
        d3.setDateOfBirth(new Date(2015, 10, 25));
        d3.setGender(Gender.MALE);
        dogs.add(d3);

        return dogs;
    }

    private void createDogs(List<Dog> dogs){
        for (Dog dog: dogs) {
            this.createDog(dog);
        }
    }

    private void createDog(Dog dog){
        this.dogService.create(dog);
    }

    private List<Employee> loadEmployees(){
        List<Employee> employees = new ArrayList<>();

        Employee e1 = new Employee();
        e1.setName("Honza");
        e1.setSurname("Nuzka");
        e1.setSalary(new BigDecimal("20000"));
        e1.setAddress(new Address("Brno", "Botanicka", 68));
        e1.setPhoneNumber("0902222333");
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setName("Julius");
        e2.setSurname("Rucka");
        e2.setSalary(new BigDecimal("29000"));
        e2.setAddress(new Address("Praha", "Galvaniho", 1));
        e2.setPhoneNumber("0947691644");
        employees.add(e2);

        return employees;
    }

    private void createEmployees(List<Employee> employees){
        for (Employee employee: employees) {
            this.createEmployee(employee);
        }
    }

    private void createEmployee(Employee employee){
        this.employeeService.create(employee);
    }

    private List<ServiceType> loadServiceTypes(){
        List<ServiceType> serviceTypes = new ArrayList<>();

        ServiceType st1 = new ServiceType("Cutting - basic", "Basic cutting for any breed of dog", new BigDecimal("100"));
        serviceTypes.add(st1);

        ServiceType st2 = new ServiceType("Cutting - with belly massage", "Cutting for dog of any breed with additional belly rubbing for maximum comfort", new BigDecimal("180"));
        serviceTypes.add(st2);

        return serviceTypes;
    }

    private void createServiceTypes(List<ServiceType> serviceTypes){
        for (ServiceType serviceType: serviceTypes) {
            this.createServiceType(serviceType);
        }
    }

    private void createServiceType(ServiceType serviceType){
        this.serviceTypeService.create(serviceType);
    }

    private List<ServiceRecord> loadServiceRecords(){
        List<ServiceRecord> serviceRecords = new ArrayList<>();

        ServiceRecord sr1 = new ServiceRecord();
        sr1.setLengthMinutes(30);
        sr1.setDateProvided(new Date(2017, 12, 14));
        sr1.setActualPrice(new BigDecimal("100"));
        serviceRecords.add(sr1);

        ServiceRecord sr2 = new ServiceRecord();
        sr2.setLengthMinutes(35);
        sr2.setDateProvided(new Date(2017, 12, 13));
        sr2.setActualPrice(new BigDecimal("100"));
        serviceRecords.add(sr2);

        ServiceRecord sr3 = new ServiceRecord();
        sr3.setLengthMinutes(45);
        sr3.setDateProvided(new Date(2017, 11, 10));
        sr3.setActualPrice(new BigDecimal("180"));
        serviceRecords.add(sr3);

        return serviceRecords;
    }

    private void createServiceRecords(List<ServiceRecord> serviceRecords){
        for (ServiceRecord serviceRecord: serviceRecords) {
            this.createServiceRecord(serviceRecord);
        }
    }

    private void createServiceRecord(ServiceRecord serviceRecord){
        this.serviceRecordService.create(serviceRecord);
    }
}
