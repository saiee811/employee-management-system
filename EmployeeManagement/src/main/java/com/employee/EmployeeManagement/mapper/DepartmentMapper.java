package com.employee.EmployeeManagement.mapper;

import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import com.employee.EmployeeManagement.entity.DepartmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponseDTO toDTO(DepartmentEntity departmentEntity);

    DepartmentEntity toEntity(DepartmentRequestDTO departmentResponseDTO);
}
