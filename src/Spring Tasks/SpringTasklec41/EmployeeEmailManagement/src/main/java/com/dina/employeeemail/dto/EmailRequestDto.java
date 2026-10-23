package com.dina.employeeemail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    @NotBlank(message = "Email name is required")
    private String name;

    @NotBlank(message = "Email content is required")
    @Email(message = "Email content must be a valid email address")
    private String content;

    private Long employeeId;
}
