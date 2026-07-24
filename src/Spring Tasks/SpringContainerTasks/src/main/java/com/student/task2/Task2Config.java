package com.student.task2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Task2Config {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

    @Bean
    public AccountServiceImpl accountService(PersonService personService) {
        return new AccountServiceImpl(personService);
    }
}

