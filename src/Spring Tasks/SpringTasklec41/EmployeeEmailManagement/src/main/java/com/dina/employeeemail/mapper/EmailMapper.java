package com.dina.employeeemail.mapper;

import com.dina.employeeemail.dto.EmailRequestDto;
import com.dina.employeeemail.dto.EmailResponseDto;
import com.dina.employeeemail.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email toEntity(EmailRequestDto dto) {
        Email email = new Email();
        email.setName(dto.getName());
        email.setContent(dto.getContent());
        return email;
    }

    public EmailResponseDto toDto(Email email) {
        Long employeeId = email.getEmployee() == null ? null : email.getEmployee().getId();
        return new EmailResponseDto(email.getId(), email.getName(), email.getContent(), employeeId);
    }
}
