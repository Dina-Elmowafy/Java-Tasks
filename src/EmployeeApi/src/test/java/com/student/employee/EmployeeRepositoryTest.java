package com.student.employee;

import com.student.employee.model.Employee;
import com.student.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @BeforeEach
    void addTestData() {
        repository.save(new Employee("Ahmed Ali", 25, "01012345678"));
        repository.save(new Employee("Ahmed Samy", 30, "01112345678"));
        repository.save(new Employee("Mona Adel", 28, "01212345678"));
    }

    @Test
    void allThreeSearchMethodsFindNamesStartingWithAhmed() {
        assertThat(repository.findByNameStartingWithIgnoreCase("ahmed")).hasSize(2);
        assertThat(repository.searchByNameUsingJpql("ahmed")).hasSize(2);
        assertThat(repository.searchByNameUsingNativeQuery("ahmed")).hasSize(2);
    }
}
