package com.jpa.model.entity;

import com.jpa.common.Gender;
import com.jpa.model.entity.dto.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="STUDENT")
@SequenceGenerator(name="seqstudentNo", sequenceName = "seq_studentno", allocationSize=1 , initialValue=10)
public class StudentEntity {


    @Id
    @GeneratedValue(generator = "seqstudentNo", strategy = GenerationType.SEQUENCE)
    private Long studentNo;

    @Column(name="student_name", nullable=false)
    private String studentName;

    @Column(name="student_grade")
    private Integer grade;


    private Integer classNum;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "Date default SYSDATE")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Embedded
    private Address address;

}
