package com.employee.EmployeeManagement.dto;

import com.employee.EmployeeManagement.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListDTO {

    private Long empId;
    private String empName;
    private String email;
    private String phoneNo;
    private String departmentName;
    private String designation;
    private EmployeeStatus status;
    private Long managerId;
}
