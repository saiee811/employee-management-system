package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import com.employee.EmployeeManagement.entity.DepartmentEntity;
import com.employee.EmployeeManagement.exception.DepartmentAlreadyExistsException;
import com.employee.EmployeeManagement.mapper.DepartmentMapper;
import com.employee.EmployeeManagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private  final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {

        if(departmentRepository.existsByName(departmentRequestDTO.getName())){
            throw new DepartmentAlreadyExistsException("Department already exists with name: "+departmentRequestDTO.getName());
        }

        DepartmentEntity departmentEntity = departmentMapper.toEntity(departmentRequestDTO);
        DepartmentEntity department = departmentRepository.save(departmentEntity);
        return departmentMapper.toDTO(department);
    }
}
