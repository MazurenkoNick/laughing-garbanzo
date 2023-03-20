package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    DepartmentService departmentService;

    @PostMapping("")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto department = departmentService.saveDepartment(departmentDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(department);
    }

    @GetMapping("/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode) {

        DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(department);
    }
}
