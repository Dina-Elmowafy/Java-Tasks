package com.student.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task2XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task2-beans.xml");

        AccountService accountService =
                context.getBean("accountService", AccountService.class);

        accountService.getSavePerson("Sara");

        context.close();
    }
}

