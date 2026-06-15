package com.employee.EmployeeManagement.controller;

import com.employee.EmployeeManagement.common.ApiResponse;
import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceUpdateDTO;
import com.employee.EmployeeManagement.service.EmployeeExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{empId}/experiences/{expId}")
    public ResponseEntity<ApiResponse<EmployeeExperienceResponseDTO>> updateEmployeeExperience(@PathVariable Long expId, @PathVariable Long empId, @RequestBody EmployeeExperienceUpdateDTO employeeExperienceUpdateDTO){
        EmployeeExperienceResponseDTO employeeExperienceResponseDTO = employeeExperienceService.updateEmployeeExperience(expId,empId,employeeExperienceUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Employee experience updated successfully",employeeExperienceResponseDTO));
    }
    @GetMapping("/{empId}/getExperiences")
    public  ResponseEntity<ApiResponse<List<EmployeeExperienceResponseDTO>>> getEmployeeExperiences(@PathVariable Long empId){
        List<EmployeeExperienceResponseDTO> employeeExperienceResponseDTOList = employeeExperienceService.getEmployeeExperiences(empId);
        return  ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("Employee experience found ",employeeExperienceResponseDTOList));
    }

    @DeleteMapping("/{empId}/deleteEmployee/{expId}")
    public ResponseEntity<ApiResponse<String>> deleteExperience(@PathVariable Long empId, @PathVariable Long expId){
        employeeExperienceService.deleteExperience(empId,expId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Experience deleted successfully",null));
    }




}
