package com.student.task1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task1JavaMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Task1Config.class);

        PersonService personService = context.getBean(PersonService.class);
        MangerService mangerService = context.getBean(MangerService.class);

        personService.save("Ahmed");
        personService.update("Ahmed Ali");

        mangerService.save("Mona");
        mangerService.update("Mona Hassan");

        context.close();
    }
}

