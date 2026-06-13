package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.common.ApiResponse;
import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import com.employee.EmployeeManagement.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> createDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO){
        DepartmentResponseDTO departmentResponseDTO = departmentService.createDepartment(departmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Department created successfully",departmentResponseDTO));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDTO departmentRequestDTO){
        DepartmentResponseDTO departmentResponseDTO = departmentService.updateDepartment(id,departmentRequestDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Department Data updated successfull",departmentResponseDTO));
    }


}
