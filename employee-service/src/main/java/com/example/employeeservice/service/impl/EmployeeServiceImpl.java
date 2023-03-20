package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //TODO implement mapper for Employee entity and EmployeeDto
        Employee transientEmployee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        Employee employee = employeeRepository.save(transientEmployee);

        //TODO implement mapper for Employee entity and EmployeeDto
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = optionalEmployee.orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with id %s was not found", id))
        );

        //TODO implement mapper for Employee entity and EmployeeDto
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}
