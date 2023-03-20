package com.example.departmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message="Can't be null")
    private String departmentName;

    private String departmentDescription;

    @NotBlank(message="Can't be null")
    private String departmentCode;
}
