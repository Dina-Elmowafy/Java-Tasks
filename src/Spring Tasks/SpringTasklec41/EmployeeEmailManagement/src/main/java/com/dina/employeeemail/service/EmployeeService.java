package com.dina.employeeemail.service;

import com.dina.employeeemail.dto.EmployeeRequestDto;
import com.dina.employeeemail.dto.EmployeeResponseDto;
import com.dina.employeeemail.mapper.EmailMapper;
import com.dina.employeeemail.mapper.EmployeeMapper;
import com.dina.employeeemail.model.Employee;
import com.dina.employeeemail.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmailMapper emailMapper;

    public EmployeeResponseDto create(EmployeeRequestDto dto) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(dto)));
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto dto) {
        Employee employee = findEmployee(id);
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());
        employee.getEmails().clear();
        if (dto.getEmails() != null) {
            dto.getEmails().forEach(emailDto -> employee.addEmail(emailMapper.toEntity(emailDto)));
        }
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void delete(Long id) {
        employeeRepository.delete(findEmployee(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAll() {
        return mapList(employeeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getById(Long id) {
        return employeeMapper.toDto(findEmployee(id));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getByIds(List<Long> ids) {
        return mapList(employeeRepository.findAllById(ids));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getByNames(List<String> names) {
        return mapList(employeeRepository.findByNameIn(names));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    private List<EmployeeResponseDto> mapList(List<Employee> employees) {
        return employees.stream().map(employeeMapper::toDto).toList();
    }
}
