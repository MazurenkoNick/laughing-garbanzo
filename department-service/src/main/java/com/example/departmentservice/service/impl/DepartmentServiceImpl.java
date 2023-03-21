package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.mapper.DepartmentMapper;
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

        Department transientDepartment = DepartmentMapper.INSTANCE.dtoToDepartment(departmentDto);

        Department department = departmentRepository.save(transientDepartment);

        return DepartmentMapper.INSTANCE.departmentToDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);

        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Department with code %s was not found", departmentCode));
        }

        Department department = optionalDepartment.get();

        return DepartmentMapper.INSTANCE.departmentToDto(department);
    }

}
