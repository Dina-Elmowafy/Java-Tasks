package com.student.task1;

public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("Person updated: " + name);
    }
}

