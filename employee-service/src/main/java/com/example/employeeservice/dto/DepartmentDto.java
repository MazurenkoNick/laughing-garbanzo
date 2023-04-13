package com.example.employeeservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {

    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

}