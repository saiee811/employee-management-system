package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.entity.EmployeeExperienceEntity;
import com.employee.EmployeeManagement.exception.EmployeeNotFoundException;
import com.employee.EmployeeManagement.mapper.EmployeeExperienceMapper;
import com.employee.EmployeeManagement.repository.EmployeeExperienceRepository;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeExperienceServiceImpl implements EmployeeExperienceService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeExperienceRepository employeeExperienceRepository;
    private  final EmployeeExperienceMapper employeeExperienceMapper;



    @Override
    public EmployeeExperienceResponseDTO addEmployeeExperience(Long empId,EmployeeExperienceRequestDTO employeeExperienceRequestDTO) {

        EmployeeEntity employeeEntity = employeeRepository.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Please enter valid employee Id"));

        EmployeeExperienceEntity employeeExperienceEntity = employeeExperienceMapper.toEntity(employeeExperienceRequestDTO);
        employeeExperienceEntity.setEmployee(employeeEntity);
        EmployeeExperienceEntity employeeExperience = employeeExperienceRepository.save(employeeExperienceEntity);
        EmployeeExperienceResponseDTO employeeExperienceResponseDTO = employeeExperienceMapper.toDTO(employeeExperience);
        employeeExperienceResponseDTO.setEmployeeId(empId);



        return employeeExperienceResponseDTO;
    }
}
