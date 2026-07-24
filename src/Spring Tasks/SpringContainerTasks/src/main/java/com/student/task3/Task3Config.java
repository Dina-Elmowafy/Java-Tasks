package com.student.task3;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Task3Config {

    @Bean(initMethod = "initService", destroyMethod = "destroyService")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PersonService personService() {
        return new PersonService();
    }
}

