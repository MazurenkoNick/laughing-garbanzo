package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            throw new IllegalArgumentException("Department can not be null");
        }

        // TODO add mapper to convert from department entity to DTO
        Department transientDepartment = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department department = departmentRepository.save(transientDepartment);

        // TODO add mapper to convert from department entity to DTO
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);

        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Department with code %s was not found", departmentCode));
        }

        Department department = optionalDepartment.get();
        // TODO add mapper to convert from department entity to DTO
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        ) ;
    }

}
