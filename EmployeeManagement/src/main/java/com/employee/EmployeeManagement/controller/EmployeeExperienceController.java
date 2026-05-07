package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.common.ApiResponse;
import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.service.EmployeeExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeExperienceController {

    private  final EmployeeExperienceService employeeExperienceService;

    @PostMapping("/{empId}/experience/")
    public ResponseEntity<ApiResponse<EmployeeExperienceResponseDTO>> addExployeeExperience(@PathVariable Long empId,@Valid @RequestBody EmployeeExperienceRequestDTO employeeExperienceRequestDTO){
        EmployeeExperienceResponseDTO employeeExperienceResponseDTO = employeeExperienceService.addEmployeeExperience(empId,employeeExperienceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Employee experience added successfully",employeeExperienceResponseDTO));

    }





}
