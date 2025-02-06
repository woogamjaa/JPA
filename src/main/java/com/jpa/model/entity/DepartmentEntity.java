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
@SequenceGenerator(name="seqdepartmentNo", sequenceName = "seq_department_no", allocationSize=1)
public class DepartmentEntity {

    @Id
    @Column(name="department_No")
    @GeneratedValue(generator = "seqdepartmentNo", strategy = GenerationType.SEQUENCE)
    private Long departmentNo;

    @Column(name="department_name", nullable=false)
    private String departmentName;


    @OneToMany(mappedBy="department") //employee에서 설정한 필드값
    private List<EmployeeEntity> employeeName;
}
