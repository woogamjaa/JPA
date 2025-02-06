package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="department")
@SequenceGenerator(name="seqdepartmentNo", sequenceName = "seq_department_no", allocationSize=1 , initialValue=1)
public class DepartmentEntity {
    @Id
    @Column(name="department_No")
    @GeneratedValue(generator = "seqdepartmentNo", strategy = GenerationType.SEQUENCE)
    private Long departmentNo;

    @Column(name="department_name", nullable=false)
    private String departmentName;

//    @JoinColumn(name="employee_Name")
    @ToString.Exclude
    @OneToMany(mappedBy="department")
    private List<EmployeeEntity> employeeName;
}
