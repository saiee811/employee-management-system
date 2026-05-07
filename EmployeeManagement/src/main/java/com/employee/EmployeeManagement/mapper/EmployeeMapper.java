package com.employee.EmployeeManagement.mapper;

import com.employee.EmployeeManagement.dto.EmployeeListDTO;
import com.employee.EmployeeManagement.dto.EmployeeRequestDTO;
import com.employee.EmployeeManagement.dto.EmployeeResponseDTO;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "manager.empId", target = "managerId")
    @Mapping(source = "manager.empName", target = "managerName")
    EmployeeResponseDTO toDTO(EmployeeEntity employeeEntity);

    @Mapping(target = "department", ignore = true)
    @Mapping(target = "manager", ignore = true)
    EmployeeEntity toEntity(EmployeeRequestDTO dto);

    @Mapping(source = "department.name", target = "departmentName")
    EmployeeListDTO toListDTO(EmployeeEntity employee);

}
