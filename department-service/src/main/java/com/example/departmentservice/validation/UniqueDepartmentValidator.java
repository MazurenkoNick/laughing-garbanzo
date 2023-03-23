package com.example.departmentservice.validation;

import com.example.departmentservice.repository.DepartmentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueDepartmentValidator implements ConstraintValidator<UniqueDepartment, String> {

    private final DepartmentRepository departmentRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return departmentRepository.findByDepartmentCode(value).isEmpty();
    }
}
