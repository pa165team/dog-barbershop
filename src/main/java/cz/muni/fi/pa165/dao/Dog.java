package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.enums.Gender;

public class Dog {

    private String Name;

    private String Breed;

    private int Age;

    private cz.muni.fi.pa165.enums.Gender Gender;

    public Dog(){}

    public Dog(String name, String breed, int age, Gender gender) {
        Name = name;
        Breed = breed;
        Age = age;
        Gender = gender;
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
