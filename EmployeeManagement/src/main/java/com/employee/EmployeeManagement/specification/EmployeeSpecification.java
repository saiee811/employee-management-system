package com.employee.EmployeeManagement.specification;

import com.employee.EmployeeManagement.entity.EmployeeEntity;
import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<EmployeeEntity> hasStatus(EmployeeStatus status){
        return (root,query,criteriaBuilder)->{
            if(status==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<EmployeeEntity> hasDepartment(Long departmentId){
        return (root, query, criteriaBuilder) -> {
            if(departmentId==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("department").get("id"),departmentId);
        };
    }

    public static Specification<EmployeeEntity> hasDesignation(String designation){
        return (root, query, criteriaBuilder) ->{
            if(designation == null || designation.trim().isEmpty()){
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("designation")),"%"+designation.toLowerCase()+"%");
        } ;
    }

    public static Specification<EmployeeEntity> hasEmploymentType(EmploymentType employmentType){
        return (root, query, criteriaBuilder) -> {
            if(employmentType==null){
                return null;
            }
            return criteriaBuilder.equal(root.get("employmentType"),employmentType);
        };
    }
}
