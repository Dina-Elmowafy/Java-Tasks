package com.dina.employeeemail.service;

import com.dina.employeeemail.dto.EmailRequestDto;
import com.dina.employeeemail.dto.EmailResponseDto;
import com.dina.employeeemail.mapper.EmailMapper;
import com.dina.employeeemail.model.Email;
import com.dina.employeeemail.model.Employee;
import com.dina.employeeemail.repository.EmailRepository;
import com.dina.employeeemail.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailMapper emailMapper;

    public EmailResponseDto create(EmailRequestDto dto) {
        Email email = emailMapper.toEntity(dto);
        setEmployee(email, dto.getEmployeeId());
        return emailMapper.toDto(emailRepository.save(email));
    }

    public EmailResponseDto update(Long id, EmailRequestDto dto) {
        Email email = findEmail(id);
        email.setName(dto.getName());
        email.setContent(dto.getContent());
        setEmployee(email, dto.getEmployeeId());
        return emailMapper.toDto(emailRepository.save(email));
    }

    public void delete(Long id) {
        emailRepository.delete(findEmail(id));
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getAll() {
        return mapList(emailRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByName(String name) {
        return mapList(emailRepository.findByName(name));
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByNames(List<String> names) {
        return mapList(emailRepository.findByNameIn(names));
    }

    @Transactional(readOnly = true)
    public List<EmailResponseDto> getByContent(String content) {
        return mapList(emailRepository.findByContent(content));
    }

    private void setEmployee(Email email, Long employeeId) {
        if (employeeId == null) {
            email.setEmployee(null);
            return;
        }
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
        email.setEmployee(employee);
    }

    private Email findEmail(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Email not found with id: " + id));
    }

    private List<EmailResponseDto> mapList(List<Email> emails) {
        return emails.stream().map(emailMapper::toDto).toList();
    }
}
