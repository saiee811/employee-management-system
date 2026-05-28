package com.employee.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DepartmentRequestDTO {
    @NotBlank
    private String name;
}