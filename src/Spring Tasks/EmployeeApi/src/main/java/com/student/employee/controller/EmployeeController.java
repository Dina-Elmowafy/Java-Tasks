package Api.controller;

import Api.model.Employee;
import Api.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private EmployeeService employeeService;
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
       return employeeService.getEmployees();
    }

    @PostMapping("/createEmployees")
    public List<Employee> createEmployees(@RequestBody List<Employee> employees) {
        return employeeService.createEmployees(employees);
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }

   @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(){
        employeeService.deleteAllEmployee();
    }
    @PostMapping("/getEmployeesByID")
    public List<Employee> getEmployeesByID (@RequestBody List<Integer> ids){
        return  employeeService.getEmployeesByID(ids);
    }
    @PutMapping("/updateEmployees")
    public List<Employee> updateEmployees(@RequestBody List<Employee> employees){
        return employeeService.updateEmployees(employees);
    }
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }
    @DeleteMapping("/deleteEmployeeById/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/deleteEmployees")
    public void deleteEmployees(@RequestBody List<Integer> ids){
        employeeService.deleteEmployees(ids);
    }
    @GetMapping("searchByName")
    public List<Employee> searchByName(String name){
        return employeeService.searchByName(name);
    }
    @GetMapping("searchByNameNative")
    public List<Employee> searchByNameNative(String name){
        return employeeService.searchByNameNative(name);
    }
}


