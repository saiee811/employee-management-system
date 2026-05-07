package com.employee.EmployeeManagement.dto;

import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponseDTO {

    private Long empId;
    private String empName;
    private String email;
    private String phoneNo;
    private String departmentName; // 👈 not object
    private String designation;
    private EmploymentType employmentType;
    private EmployeeStatus status;
    private BigDecimal salary;
    private BigDecimal bonus;
    private Long managerId;
    private String managerName;
    private LocalDate joiningDateCurrentOrg;
}