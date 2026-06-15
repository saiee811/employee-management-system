package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceUpdateDTO;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.entity.EmployeeExperienceEntity;
import com.employee.EmployeeManagement.exception.EmployeeNotFoundException;
import com.employee.EmployeeManagement.mapper.EmployeeExperienceMapper;
import com.employee.EmployeeManagement.repository.EmployeeExperienceRepository;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(employeeExperienceRequestDTO.getExitDate()!=null && employeeExperienceRequestDTO.getExitDate().isBefore(employeeExperienceRequestDTO.getJoiningDate())){
            throw new IllegalArgumentException("Exit date cannot be before joining date!");
        }
        EmployeeExperienceEntity employeeExperience = employeeExperienceRepository.save(employeeExperienceEntity);
        EmployeeExperienceResponseDTO employeeExperienceResponseDTO = employeeExperienceMapper.toDTO(employeeExperience);
        employeeExperienceResponseDTO.setEmployeeId(empId);
        return employeeExperienceResponseDTO;
    }

    public EmployeeExperienceResponseDTO updateEmployeeExperience(Long id, Long empId,EmployeeExperienceUpdateDTO employeeExperienceUpdateDTO) {
        EmployeeExperienceEntity employeeExperienceEntity = employeeExperienceRepository.findByIdAndEmployeeEmpId(id, empId).orElseThrow(() -> new IllegalArgumentException("Employee with this experience does not exists"));
        if (employeeExperienceUpdateDTO.getCompanyName() != null) {
            employeeExperienceEntity.setCompanyName(employeeExperienceUpdateDTO.getCompanyName());
        }
        if (employeeExperienceUpdateDTO.getDesignation() != null) {
            employeeExperienceEntity.setDesignation(employeeExperienceUpdateDTO.getDesignation());
        }
        if (employeeExperienceUpdateDTO.getJoiningDate() != null) {
            employeeExperienceEntity.setJoiningDate(employeeExperienceUpdateDTO.getJoiningDate());
        }
        if (employeeExperienceUpdateDTO.getExitDate() != null) {
            employeeExperienceEntity.setExitDate(employeeExperienceUpdateDTO.getExitDate());
        }
        if (employeeExperienceEntity.getExitDate() != null && employeeExperienceEntity.getExitDate().isBefore(employeeExperienceEntity.getJoiningDate())) {
            throw new IllegalArgumentException("exit date cannot be before joining date");
        }

        EmployeeExperienceEntity employeeExperiences = employeeExperienceRepository.save(employeeExperienceEntity);
        return employeeExperienceMapper.toDTO(employeeExperiences);

    }
    // TODO: Fix employeeId mapping in EmployeeExperienceMapper
    public List<EmployeeExperienceResponseDTO> getEmployeeExperiences(Long empId){
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee not found with empId : + empId)"));
        List<EmployeeExperienceEntity> employeeExperienceEntityList = employeeExperienceRepository.findAllByEmployeeEmpId(empId);
        return employeeExperienceEntityList.stream().map(employeeExperienceMapper::toDTO).toList();
    }
    public void deleteExperience(Long expId, Long empId){
        EmployeeExperienceEntity employeeExperienceEntity = employeeExperienceRepository.findByIdAndEmployeeEmpId(expId,empId).orElseThrow(()->new EmployeeNotFoundException("Experience not found"));
        employeeExperienceRepository.delete(employeeExperienceEntity);
    }

}
