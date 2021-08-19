package com.datasphera.drools.Models;

public class Applicant {
    private String name;
    private int age;

    public Applicant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + name + "'" +
            ", age='" + age + "'" +
            "}";
    }
}
