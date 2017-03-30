package com.example.pprabhakaran.myapplication;

/**
 * Created by pprabhakaran on 3/30/17.
 */

public class Person {

    public enum Gender  { MALE, FEMALE }

    private final String name;
    private final int age;
    private final Gender gender;

    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
