package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-30T15:20:39+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Amazon.com Inc.)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto departmentToDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setDepartmentName( department.getDepartmentName() );
        departmentDto.setDepartmentDescription( department.getDepartmentDescription() );
        departmentDto.setDepartmentCode( department.getDepartmentCode() );

        return departmentDto;
    }

    @Override
    public Department dtoToDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentName( departmentDto.getDepartmentName() );
        department.setDepartmentDescription( departmentDto.getDepartmentDescription() );
        department.setDepartmentCode( departmentDto.getDepartmentCode() );

        return department;
    }
}
