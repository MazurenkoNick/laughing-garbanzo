package com.example.employeeservice.dto;

import com.example.employeeservice.validation.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @UniqueEmail
    private String email;

    private String departmentCode;
}
