package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name="employee")
@SequenceGenerator(name="seqemployeeNo", sequenceName = "seq_employee_no", allocationSize=1)
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

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="department_ref") //포린키 값을 받는 컬럼이니까 . 컬럼이랑 똑같다. 제약조건 가능.
    private DepartmentEntity department;

    //세터를 만든다.
    public void setDepartment(DepartmentEntity department) {
        if (this.department != null && this.department.getEmployeeName()!=null) {
            this.department.getEmployeeName().remove(this);
        }
        this.department = department;
        if(this.department.getEmployeeName()==null) {
            this.department.setEmployeeName(new ArrayList<>());
        }
        this.department.getEmployeeName().add(this);

    }
}
