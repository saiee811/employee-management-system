package com.employee.EmployeeManagement.service;

import com.employee.EmployeeManagement.dto.*;
import com.employee.EmployeeManagement.entity.DepartmentEntity;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import com.employee.EmployeeManagement.exception.DepartmentNotFoundException;
import com.employee.EmployeeManagement.exception.EmployeeAlredayExistsException;
import com.employee.EmployeeManagement.exception.EmployeeNotFoundException;
import com.employee.EmployeeManagement.exception.ManagerNotFoundException;
import com.employee.EmployeeManagement.mapper.EmployeeMapper;
import com.employee.EmployeeManagement.repository.DepartmentRepository;
import com.employee.EmployeeManagement.repository.EmployeeRepository;
import com.employee.EmployeeManagement.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new EmployeeAlredayExistsException("Employee already exists with same email id!" + employeeRequestDTO.getEmail());
        }
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeRequestDTO);

        DepartmentEntity department = departmentRepository.findById(employeeRequestDTO.getDepartmentId()).orElseThrow(() -> new DepartmentNotFoundException("This Department does not exist"));
        employeeEntity.setDepartment(department);

        if (employeeRequestDTO.getManagerId() != null) {
            EmployeeEntity manager = employeeRepository.findById(employeeRequestDTO.getManagerId()).orElseThrow(() -> new ManagerNotFoundException("Manager does not exists"));
            employeeEntity.setManager(manager);
        }
        EmployeeEntity employee = employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long empId) {
        EmployeeEntity employeeEntity = employeeRepository.getEmployeeDetails(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with empId :" + empId));
        return employeeMapper.toDTO(employeeEntity);
    }

    @Override
    public Page<EmployeeListDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.getAllEmployees(pageable);
    }

    @Override
    public Page<EmployeeListDTO> getEmployeesUsingFilter(EmployeeStatus status, Long departmentId, String designation, EmploymentType employmentType, String empName, Pageable pageable) {
        Specification<EmployeeEntity> spec = Specification.where(null);
        spec = spec.and(EmployeeSpecification.hasStatus(status));
        spec = spec.and(EmployeeSpecification.hasDepartment(departmentId));
        spec = spec.and(EmployeeSpecification.hasDesignation(designation));
        spec = spec.and(EmployeeSpecification.hasEmploymentType(employmentType));
        spec = spec.and(EmployeeSpecification.hasEmpName(empName));
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(spec, pageable);
        return employeeEntities.map(employeeMapper::toListDTO);
    }

    @Override
    public EmployeeResponseDTO updateEmployeeDetails(Long empId, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee with this empId does not exists"));
        if (employeeUpdateRequestDTO.getDesignation() != null) {
            employeeEntity.setDesignation(employeeUpdateRequestDTO.getDesignation());
        }
        if (employeeUpdateRequestDTO.getDepartmentId() != null) {
            DepartmentEntity departmentEntity = departmentRepository.findById(employeeUpdateRequestDTO.getDepartmentId()).orElseThrow(() -> new DepartmentNotFoundException("This department does not exits"));
            employeeEntity.setDepartment(departmentEntity);
        }
        if (employeeUpdateRequestDTO.getPhoneNo() != null) {
            employeeEntity.setPhoneNo(employeeUpdateRequestDTO.getPhoneNo());
        }
        if (employeeUpdateRequestDTO.getSalary() != null){
            if(employeeUpdateRequestDTO.getSalary().signum() < 0){
                throw new IllegalArgumentException("Salary cannot be negative");
            }
            employeeEntity.setSalary(employeeUpdateRequestDTO.getSalary());
        }
        if (employeeUpdateRequestDTO.getEmploymentType() != null) {
            employeeEntity.setEmploymentType(employeeUpdateRequestDTO.getEmploymentType());
        }
        if (employeeUpdateRequestDTO.getManagerId() != null) {
            if (empId.equals(employeeUpdateRequestDTO.getManagerId())) {
                throw new IllegalArgumentException("Employee cannot be his own manager");
            }
            EmployeeEntity managerEntity = employeeRepository.findById(employeeUpdateRequestDTO.getManagerId()).orElseThrow(() -> new EmployeeNotFoundException("Manager not found!"));
            employeeEntity.setManager(managerEntity);
        }

        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(updatedEmployeeEntity);
    }


    public EmployeeResponseDTO deactivateEmployee(Long id, EmployeeStatusUpdateDTO employeeStatusUpdateDTO){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
        if(employeeEntity.getStatus()==EmployeeStatus.INACTIVE){
           throw new IllegalArgumentException("Employee is already INACTIVE in the system");
        }else{
            employeeEntity.setStatus(employeeStatusUpdateDTO.getStatus());
        }
        EmployeeEntity updatedEmployeeStatus = employeeRepository.save(employeeEntity);
        return employeeMapper.toDTO(updatedEmployeeStatus);

    }
    
}
