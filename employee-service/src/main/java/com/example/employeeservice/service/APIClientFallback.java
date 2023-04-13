package com.example.employeeservice.service;

import com.example.employeeservice.dto.DepartmentDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class APIClientFallback implements APIClient {

    @Override
    public ResponseEntity<DepartmentDto> getDepartment(String departmentCode) {
        log.error("Exception in APIClient#getDepartment(String)");
        var department = DepartmentDto.builder().build();
        ResponseEntity<DepartmentDto> responseEntity = ResponseEntity.ok().body(department);
        return responseEntity;
    }
}
