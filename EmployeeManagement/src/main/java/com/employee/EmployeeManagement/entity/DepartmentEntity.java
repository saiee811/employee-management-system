package com.employee.EmployeeManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "departments",
        indexes = {
        @Index(name = "idx_department_name", columnList = "department_name")
        }
)
@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @NotBlank
    @Column(name = "department_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<EmployeeEntity> employees;

}
