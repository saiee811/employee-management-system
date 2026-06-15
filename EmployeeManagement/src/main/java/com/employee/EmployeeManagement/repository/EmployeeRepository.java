package com.employee.EmployeeManagement.repository;

import com.employee.EmployeeManagement.dto.EmployeeListDTO;
import com.employee.EmployeeManagement.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {
    Boolean existsByEmail(String email);

    @Query("SELECT e FROM EmployeeEntity e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.manager where e.empId = :empId")
    Optional<EmployeeEntity> getEmployeeDetails(@Param("empId") Long empId);

    @Query("""
    SELECT new com.employee.EmployeeManagement.dto.EmployeeListDTO(
        e.empId,
        e.empName,
        e.email,
        e.phoneNo,
        d.name,
        e.designation,
        e.status
    )
    FROM EmployeeEntity e
    LEFT JOIN e.department d
""")
   public Page<EmployeeListDTO> getAllEmployees(Pageable pageable);
    Boolean existsByDepartmentId(Long id);
}
