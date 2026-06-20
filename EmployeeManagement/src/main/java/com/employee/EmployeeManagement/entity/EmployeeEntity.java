package com.employee.EmployeeManagement.entity;

import com.employee.EmployeeManagement.enums.EmployeeStatus;
import com.employee.EmployeeManagement.enums.EmploymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees",
        indexes ={
                @Index(name = "idx_email", columnList = "employee_email"),
                @Index(name = "idx_status", columnList = "status"),
                @Index(name = "idx_manager", columnList = "manager_id")
        }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "manager")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long empId;
    @NotBlank
    @Column(name = "employee_name",nullable = false)
    private String empName;

    @NotBlank
    @Email
    @Column(name = "employee_email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNo;

    @Column(name = "designation",nullable = false)
    private String designation;

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Column(name = "salary",precision = 10,scale = 2, nullable = false)
    @NotNull
    @Positive
    private BigDecimal salary;

    @Column(name = "bonus", precision = 10, scale = 2)
    private BigDecimal bonus;

    @Column(name="joining_date_current_org",nullable = false)
    LocalDate joiningDateCurrentOrg;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeExperienceEntity> experiences;

}
