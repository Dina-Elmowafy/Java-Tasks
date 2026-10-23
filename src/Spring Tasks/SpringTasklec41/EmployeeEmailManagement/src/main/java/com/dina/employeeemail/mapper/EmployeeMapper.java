package com.dina.employeeemail.mapper;

import com.dina.employeeemail.dto.EmployeeRequestDto;
import com.dina.employeeemail.dto.EmployeeResponseDto;
import com.dina.employeeemail.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final EmailMapper emailMapper;

    public Employee toEntity(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setSalary(dto.getSalary());

        if (dto.getEmails() != null) {
            dto.getEmails().forEach(emailDto -> employee.addEmail(emailMapper.toEntity(emailDto)));
        }
        return employee;
    }

    public EmployeeResponseDto toDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getSalary(),
                employee.getEmails().stream().map(emailMapper::toDto).toList()
        );
    }
}
