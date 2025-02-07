package com.jpa.model.복합키entity;

import com.jpa.common.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="student_entity3")
@SequenceGenerator(name="seqstudent3No", sequenceName = "seq_studentno3", allocationSize=1 , initialValue=10)
public class StudentEntity3 {


    @Id
    @Column(name="student_no")
    @GeneratedValue(generator = "seqstudent3No", strategy = GenerationType.SEQUENCE)
    private Long studentNo;

    @Column(name="student_name", nullable=false)
    private String studentName;

    @Column(name="student_grade")
    private Integer grade;


    private Integer classNum;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "student")
    private List<StudentSubjectJoinEntity2> subjects;

}
