package com.example.employeeservice.service;

import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE", fallback = APIClientFallback.class)
public interface APIClient {

    @GetMapping("api/departments/{department-code}")
    ResponseEntity<DepartmentDto> getDepartment(
            @PathVariable(value = "department-code", required = false) String departmentCode);

}
