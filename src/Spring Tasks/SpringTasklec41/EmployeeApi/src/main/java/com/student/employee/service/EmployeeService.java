package com.student.employee.service;

import com.student.employee.exception.EmployeeNotFoundException;
import com.student.employee.model.Employee;
import com.student.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByIds(List<Long> ids) {
        validateIds(ids);
        return employeeRepository.findAllById(ids);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        // The database generates the id, not the client.
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> saveEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list must not be empty");
        }
        employees.forEach(employee -> employee.setId(null));
        return employeeRepository.saveAll(employees);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee newData) {
        Employee current = findEmployee(id);
        copyData(newData, current);
        return employeeRepository.save(current);
    }

    @Transactional
    public List<Employee> updateEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list must not be empty");
        }

        List<Employee> updatedEmployees = employees.stream().map(newData -> {
            if (newData.getId() == null) {
                throw new IllegalArgumentException("Every employee needs an id for update");
            }
            Employee current = findEmployee(newData.getId());
            copyData(newData, current);
            return current;
        }).toList();

        return employeeRepository.saveAll(updatedEmployees);
    }

    @Transactional
    public void deleteAllEmployees() {
        employeeRepository.deleteAllInBatch();
    }

    @Transactional
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void deleteEmployeesByIds(List<Long> ids) {
        validateIds(ids);
        employeeRepository.deleteAllByIdInBatch(ids);
    }

    public List<Employee> searchUsingFunctionName(String name) {
        return employeeRepository.findByNameStartingWithIgnoreCase(validateName(name));
    }

    public List<Employee> searchUsingJpql(String name) {
        return employeeRepository.searchByNameUsingJpql(validateName(name));
    }

    public List<Employee> searchUsingNativeQuery(String name) {
        return employeeRepository.searchByNameUsingNativeQuery(validateName(name));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private void copyData(Employee source, Employee target) {
        target.setName(source.getName());
        target.setAge(source.getAge());
        target.setPhoneNumber(source.getPhoneNumber());
    }

    private void validateIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("ID list must not be empty");
        }
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Search name must not be empty");
        }
        return name.trim();
    }
}
