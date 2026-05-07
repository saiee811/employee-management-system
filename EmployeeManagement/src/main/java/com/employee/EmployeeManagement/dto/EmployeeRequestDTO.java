package com.employee.EmployeeManagement.dto;

import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequestDTO {

    @NotBlank
    private String empName;

    @Email
    @NotBlank
    private String email;

    @Pattern(
            regexp = "^\\+[1-9]\\d{7,14}$",
            message = "Phone number must include country code (e.g. +919876543210)"
    )
    private String phoneNo;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String designation;

    private EmploymentType employmentType;

    private EmployeeStatus status;

    @NotNull
    @Positive
    private BigDecimal salary;

    private BigDecimal bonus;

    private Long managerId;

    @NotNull
    private LocalDate joiningDateCurrentOrg;
}