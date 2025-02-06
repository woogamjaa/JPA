package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name="employee")
@SequenceGenerator(name="seqemployeeNo", sequenceName = "seq_employee_no", allocationSize=1 , initialValue=1)
public class EmployeeEntity {

    @Id
    @Column(name="employee_No")
    @GeneratedValue(generator = "seqemployeeNo", strategy = GenerationType.SEQUENCE)
    private Long employeeNo;

    @Column(name="employee_Name", nullable=false)
    private String employeeName;

    @Column(name="age")
    private Integer age;

    @Column(name="address")
    private String address;

    @Column(name="salary")
    private Integer salary;

    @ManyToOne
    private DepartmentEntity department;
}
