package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${department.baseUrl}")
    private String departmentBaseUrl;
    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final APIClient departmentServiceClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate, WebClient webClient, APIClient departmentServiceClient) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.departmentServiceClient = departmentServiceClient;
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

    private EmployeeDto retrieveEmployeeDto(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = optionalEmployee.orElseThrow(
                () -> new EntityNotFoundException(String.format("Employee with id %s was not found", id))
        );

        return EmployeeMapper.INSTANCE.employeeToDto(employee);
    }

    private DepartmentDto retrieveDepartmentDto(String departmentCode) {
        ResponseEntity<DepartmentDto> responseEntity;
        responseEntity = departmentServiceClient.getDepartment(departmentCode);
        return responseEntity.getBody();
    }
}
