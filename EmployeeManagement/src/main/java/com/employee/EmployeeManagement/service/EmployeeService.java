package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeListDTO;
import com.employee.EmployeeManagement.dto.EmployeeRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeResponseDTO;
import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

      EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
      EmployeeResponseDTO getEmployeeById(Long empId);
     Page<EmployeeListDTO> getAllEmployees(Pageable pageable);
     Page<EmployeeListDTO> getEmployeesUsingFilter(EmployeeStatus status, Long departmentId, String designation, EmploymentType employmentType, String empName,Pageable pageable);

}
