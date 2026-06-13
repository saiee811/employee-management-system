package com.employee.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeExperienceUpdateDTO {

    private String companyName;
    private LocalDate joiningDate;
    private LocalDate exitDate;
    private String designation;

}
