package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.mapper.EmployeeMapper;
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
        Employee transientEmployee = EmployeeMapper.INSTANCE.dtoToEmployee(employeeDto);
        Employee employee = employeeRepository.save(transientEmployee);

        return EmployeeMapper.INSTANCE.employeeToDto(employee);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = optionalEmployee.orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with id %s was not found", id))
        );

        return EmployeeMapper.INSTANCE.employeeToDto(employee);
    }
}
