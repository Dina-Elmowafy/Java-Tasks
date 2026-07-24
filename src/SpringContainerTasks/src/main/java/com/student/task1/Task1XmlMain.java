package com.student.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task1XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task1-beans.xml");

        UserService personService = context.getBean("personService", UserService.class);
        UserService mangerService = context.getBean("mangerService", UserService.class);

        personService.save("Ahmed");
        personService.update("Ahmed Ali");

        mangerService.save("Mona");
        mangerService.update("Mona Hassan");

        context.close();
    }
}

