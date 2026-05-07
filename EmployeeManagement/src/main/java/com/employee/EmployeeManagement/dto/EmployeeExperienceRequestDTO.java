package com.employee.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeExperienceRequestDTO {

    @NotBlank
    private String companyName;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate exitDate;

    private String designation;
}