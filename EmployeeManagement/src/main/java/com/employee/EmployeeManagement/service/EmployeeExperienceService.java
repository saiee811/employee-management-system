package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeExperienceService {

    public EmployeeExperienceResponseDTO addEmployeeExperience(Long empId,EmployeeExperienceRequestDTO employeeExperienceRequestDTO);
    public EmployeeExperienceResponseDTO updateEmployeeExperience(Long id, Long empId, EmployeeExperienceUpdateDTO employeeExperienceUpdateDTO);
    public List<EmployeeExperienceResponseDTO> getEmployeeExperiences(Long empId);
    public void deleteExperience(Long expId, Long empId);
}
