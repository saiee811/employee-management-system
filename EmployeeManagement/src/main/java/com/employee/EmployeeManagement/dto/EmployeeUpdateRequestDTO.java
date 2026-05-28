package com.employee.EmployeeManagement.dto;

import com.employee.EmployeeManagement.enums.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EmployeeUpdateRequestDTO {
    private String designation;
    private Long departmentId;
    private BigDecimal salary;
    private String phoneNo;
    private EmploymentType employmentType;
    private Long managerId;
}
