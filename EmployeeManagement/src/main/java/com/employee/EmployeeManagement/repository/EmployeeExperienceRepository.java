package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.EmployeeExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeExperienceRepository extends JpaRepository<EmployeeExperienceEntity,Long> {

    Optional<EmployeeExperienceEntity> findByIdAndEmployeeEmpId(Long id, Long empId);
    List<EmployeeExperienceEntity> findAllByEmployeeEmpId(Long empId);
}
