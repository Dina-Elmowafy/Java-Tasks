package com.student.employee.controller;

import com.student.employee.model.Employee;
import com.student.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // API to get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // POST is used because sending a body with GET is not reliable.
    @PostMapping("/by-ids")
    public List<Employee> getEmployeesByIds(@RequestBody List<Long> ids) {
        return employeeService.getEmployeesByIds(ids);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.saveEmployee(employee));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Employee>> saveEmployees(
            @Valid @RequestBody List<@Valid Employee> employees) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.saveEmployees(employees));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/batch")
    public List<Employee> updateEmployees(
            @Valid @RequestBody List<@Valid Employee> employees) {
        return employeeService.updateEmployees(employees);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-ids")
    public ResponseEntity<Void> deleteEmployeesByIds(@RequestBody List<Long> ids) {
        employeeService.deleteEmployeesByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/function")
    public List<Employee> searchUsingFunctionName(@RequestParam String name) {
        return employeeService.searchUsingFunctionName(name);
    }

    @GetMapping("/search/jpql")
    public List<Employee> searchUsingJpql(@RequestParam String name) {
        return employeeService.searchUsingJpql(name);
    }

    @GetMapping("/search/native")
    public List<Employee> searchUsingNativeQuery(@RequestParam String name) {
        return employeeService.searchUsingNativeQuery(name);
    }
}
