package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.common.ApiResponse;
import com.employee.EmployeeManagement.dto.EmployeeListDTO;
import com.employee.EmployeeManagement.dto.EmployeeRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeResponseDTO;
import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import com.employee.EmployeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Employee Profile created successfully!", employeeResponseDTO
        ));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeDetails(@PathVariable Long empId) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(empId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Employee fetched successfully", employeeResponseDTO));

    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Page<EmployeeListDTO>>> getAllEmployees(@PageableDefault(size = 10, page = 0, sort = "empName") Pageable pageable) {
        Page<EmployeeListDTO> employeeListDTO = employeeService.getAllEmployees(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Employees fetched successfully", employeeListDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeListDTO>>> getAllEmployeesByFilter(@RequestParam(required = false) EmployeeStatus employeeStatus,
                                                                                      @RequestParam(required = false) Long departmentId,
                                                                                      @RequestParam(required = false) String designation,
                                                                                      @RequestParam(required = false) EmploymentType employmentType, @PageableDefault(size = 10, sort = "empName") Pageable pageable) {
        Page<EmployeeListDTO> employeeListDTOS = employeeService.getEmployeesUsingFilter(employeeStatus, departmentId, designation, employmentType, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Employees fetched successfully", employeeListDTOS));
    }
}
