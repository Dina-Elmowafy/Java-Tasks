package com.student.task2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task2JavaMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Task2Config.class);

        AccountService accountService = context.getBean(AccountService.class);
        accountService.getSavePerson("Sara");

        context.close();
    }
}

