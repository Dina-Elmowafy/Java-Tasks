package com.student.employee.repository;

import com.student.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // 1) Derived query: Spring understands the function name.
    List<Employee> findByNameStartingWithIgnoreCase(String name);

    // 2) JPQL: works with the Entity and its Java field names.
    @Query("""
            SELECT e FROM Employee e
            WHERE LOWER(e.name) LIKE LOWER(CONCAT(:name, '%'))
            """)
    List<Employee> searchByNameUsingJpql(@Param("name") String name);

    // 3) Native Oracle SQL: works with the real table and column names.
    // Oracle joins text using ||.
    @Query(value = """
            SELECT * FROM EMPLOYEES
            WHERE LOWER(NAME) LIKE LOWER(:name || '%')
            ORDER BY ID
            """, nativeQuery = true)
    List<Employee> searchByNameUsingNativeQuery(@Param("name") String name);
}
