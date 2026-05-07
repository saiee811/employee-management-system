package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentResponseDTO);
}
