package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${department.baseUrl}")
    private String departmentBaseUrl;
    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee transientEmployee = EmployeeMapper.INSTANCE.dtoToEmployee(employeeDto);
        Employee employee = employeeRepository.save(transientEmployee);

        return EmployeeMapper.INSTANCE.employeeToDto(employee);
    }

    @Override
    public ApiResponseDto getEmployee(Long id) {
        EmployeeDto employeeDto = retrieveEmployeeDto(id);
        DepartmentDto departmentDto = retrieveDepartmentDto(employeeDto.getDepartmentCode());

        return new ApiResponseDto(employeeDto, departmentDto);
    }

    private DepartmentDto retrieveDepartmentDto(String departmentCode) {
        String fullUrl = departmentBaseUrl + departmentCode;
        ResponseEntity<DepartmentDto> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(fullUrl, DepartmentDto.class);
        }
        catch (RestClientException e) {
            return null;
        }

        return responseEntity.getBody();
    }

    private EmployeeDto retrieveEmployeeDto(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = optionalEmployee.orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with id %s was not found", id))
        );

        return EmployeeMapper.INSTANCE.employeeToDto(employee);
    }
}
