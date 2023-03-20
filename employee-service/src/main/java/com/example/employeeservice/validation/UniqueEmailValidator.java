package com.example.employeeservice.validation;

import com.example.employeeservice.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return employeeRepository.findByEmail(value).isEmpty();
    }
}
