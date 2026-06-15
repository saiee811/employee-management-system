package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentResponseDTO);
    public DepartmentResponseDTO updateDepartment(Long id,DepartmentRequestDTO departmentRequestDTO);
    public DepartmentResponseDTO getDepartmentById(Long id);
    public List<DepartmentResponseDTO> getAllDepartments();
    public void deleteDepartment(Long id);
}
