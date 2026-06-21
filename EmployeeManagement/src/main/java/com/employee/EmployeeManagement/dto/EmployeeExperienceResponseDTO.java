package com.employee.EmployeeManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeExperienceResponseDTO {

    private Long Id;
    private Long employeeId;
    private String companyName;
    private LocalDate joiningDate;
    private LocalDate exitDate;
    private String designation;
}