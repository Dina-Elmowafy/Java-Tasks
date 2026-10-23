package com.student.task3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task3JavaMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Task3Config.class);

        PersonService firstPerson = context.getBean(PersonService.class);
        PersonService secondPerson = context.getBean(PersonService.class);

        firstPerson.save("Omar");
        secondPerson.save("Ali");
        System.out.println("Same object: " + (firstPerson == secondPerson));

        firstPerson.destroyService();
        secondPerson.destroyService();
        context.close();
    }
}

