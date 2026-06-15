package com.employee.EmployeeManagement.dto;

import com.employee.EmployeeManagement.enums.EmployeeStatus;
import lombok.Data;

@Data
public class EmployeeStatusUpdateDTO {
    private EmployeeStatus status;
}
