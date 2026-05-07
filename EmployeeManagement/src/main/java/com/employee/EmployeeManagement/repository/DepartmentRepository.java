package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

    Boolean existsByName(String name);
}
