package com.student.task3;

public class PersonService implements UserService {

    public void initService() {
        System.out.println("PersonService init method");
    }

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }

    public void destroyService() {
        System.out.println("PersonService destroy method");
    }
}

