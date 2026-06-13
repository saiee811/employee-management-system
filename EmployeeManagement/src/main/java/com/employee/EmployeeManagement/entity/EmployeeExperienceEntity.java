package com.employee.EmployeeManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="employee_experience",
        indexes = {
                @Index(name = "idx_employee_experience_employee",
                        columnList = "employee_id")
        }
)
@Entity
public class EmployeeExperienceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @NotBlank
    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @NotNull
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    @Column(name = "designation", length = 100)
    private String designation;
}
