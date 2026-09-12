package Api.service;

import Api.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmployees();
    public void deleteAllEmployee();
    public Employee updateEmployee(Employee employee);
    public List<Employee> createEmployees(List<Employee> employees);
    public List<Employee> getEmployeesByID (List<Integer> ids);
    public Employee createEmployee(Employee employee);
    List<Employee> updateEmployees(List<Employee> employees);
    public void deleteEmployee(int id);
    public void deleteEmployees(List<Integer> ids);
    public List<Employee> searchByName(String name);
    public List<Employee> searchByNameNative(String name);





}
