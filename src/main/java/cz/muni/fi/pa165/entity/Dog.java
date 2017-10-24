package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.Gender;

public class Dog {

    private Long Id;

    private String Name;

    private String Breed;

    private int Age;

    private Gender Gender;

    public Dog(){}

    public Dog(String name, String breed, int age, Gender gender) {
        Name = name;
        Breed = breed;
        Age = age;
        Gender = gender;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getBreed() {
        return Breed;
    }

    public int getAge() {
        return Age;
    }

    public Gender getGender() {
        return Gender;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setGender(Gender gender) {
        Gender = gender;
    }
}
