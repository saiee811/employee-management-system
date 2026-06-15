package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.DepartmentRequestDTO;
import com.employee.EmployeeManagement.dto.DepartmentResponseDTO;
import com.employee.EmployeeManagement.entity.DepartmentEntity;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.exception.DepartmentAlreadyExistsException;
import com.employee.EmployeeManagement.exception.DepartmentNotFoundException;
import com.employee.EmployeeManagement.mapper.DepartmentMapper;
import com.employee.EmployeeManagement.repository.DepartmentRepository;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private  final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeRepository employeeRepository;
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {

        if(departmentRepository.existsByName(departmentRequestDTO.getName())){
            throw new DepartmentAlreadyExistsException("Department already exists with name: "+departmentRequestDTO.getName());
        }

        DepartmentEntity departmentEntity = departmentMapper.toEntity(departmentRequestDTO);
        DepartmentEntity department = departmentRepository.save(departmentEntity);
        return departmentMapper.toDTO(department);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO departmentRequestDTO) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));

        if(departmentRequestDTO.getName()!=null && !departmentRequestDTO.getName().isBlank()){
            String updatedName = departmentRequestDTO.getName().trim();
            if(departmentRepository.existsByName(updatedName) && !departmentEntity.getName().equalsIgnoreCase(updatedName)){
                throw new DepartmentAlreadyExistsException("This department already exists");
            }
            departmentEntity.setName(updatedName);
        }
        DepartmentEntity updatedDepartments = departmentRepository.save(departmentEntity);
        return departmentMapper.toDTO(updatedDepartments);
    }
    public DepartmentResponseDTO getDepartmentById(Long id){
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        return departmentMapper.toDTO(departmentEntity);
    }
    public List<DepartmentResponseDTO> getAllDepartments(){
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
       return departmentEntities.stream().map(departmentMapper::toDTO).toList();
    }
    public void deleteDepartment(Long id){
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        boolean employeesExist =
                employeeRepository.existsByDepartmentId(id);

        if (employeesExist) {
            throw new IllegalArgumentException(
                    "Cannot delete department. Please reassign all employees before deleting the department.");
        }
        departmentRepository.delete(departmentEntity);
    }
}
