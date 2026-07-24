package com.dina.employeeemail.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    @NotBlank(message = "Employee name is required")
    private String name;

    @Min(value = 16, message = "Age must be greater than 15")
    @Max(value = 39, message = "Age must be less than 40")
    private Integer age;

    @DecimalMin(value = "5000", inclusive = false, message = "Salary must be greater than 5000")
    @DecimalMax(value = "10000", inclusive = false, message = "Salary must be less than 10000")
    private Double salary;

    @Valid
    private List<EmailRequestDto> emails = new ArrayList<>();
}
