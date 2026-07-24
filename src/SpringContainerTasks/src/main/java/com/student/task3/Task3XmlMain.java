package com.student.task3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task3XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("task3-beans.xml");

        PersonService firstPerson = context.getBean("personService", PersonService.class);
        PersonService secondPerson = context.getBean("personService", PersonService.class);

        firstPerson.save("Omar");
        secondPerson.save("Ali");
        System.out.println("Same object: " + (firstPerson == secondPerson));

        firstPerson.destroyService();
        secondPerson.destroyService();
        context.close();
    }
}

