package com.student.task1;

public class MangerService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("Manger saved: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("Manger updated: " + name);
    }
}

