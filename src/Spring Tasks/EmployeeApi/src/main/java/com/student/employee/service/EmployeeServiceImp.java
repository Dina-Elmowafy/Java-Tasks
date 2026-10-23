package Api.service;

import Api.model.Employee;
import Api.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteAllEmployee() {
         employeeRepo.deleteAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return  employeeRepo.save(employee);
    }

    @Override
    public List<Employee> createEmployees(List<Employee> employees) {
        return employeeRepo.saveAll(employees);
    }

    @Override
    public List<Employee> getEmployeesByID (List<Integer> ids){
        return  employeeRepo.findAllById(ids);
    }
    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepo.save(employee);
    }
    @Override
    public List<Employee> updateEmployees(List<Employee> employees){
        return employeeRepo.saveAll(employees);
    }
    @Override
    public void deleteEmployee(int id)
    {
        employeeRepo.deleteById(id);
    }

    @Override
    public void deleteEmployees(List<Integer> ids) {
        employeeRepo.deleteAllById(ids);
    }

    @Override
    public List<Employee> searchByName(String name){
    return employeeRepo.searchByName(name);
    }
    @Override
    public List<Employee> searchByNameNative(String name){
    return employeeRepo.searchByNameNative(name);
    }
}
