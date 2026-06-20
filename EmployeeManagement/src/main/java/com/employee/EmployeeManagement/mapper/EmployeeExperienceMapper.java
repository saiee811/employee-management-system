package com.employee.EmployeeManagement.mapper;

import com.employee.EmployeeManagement.dto.EmployeeExperienceRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeExperienceResponseDTO;
import com.employee.EmployeeManagement.entity.EmployeeExperienceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeExperienceMapper {

    @Mapping(source = "employee.empId", target = "employeeId")
    EmployeeExperienceResponseDTO toDTO(EmployeeExperienceEntity employeeExperienceEntity);

    @Mapping(target = "employee", ignore = true)
    EmployeeExperienceEntity toEntity(EmployeeExperienceRequestDTO employeeExperienceResponseDTO);

}
