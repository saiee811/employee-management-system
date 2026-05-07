package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.EmployeeExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeExperienceRepository extends JpaRepository<EmployeeExperienceEntity,Long> {
}
