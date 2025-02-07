package com.jpa.model.entity;

import com.jpa.common.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="student_entity2")
@SequenceGenerator(name="seqstudent2No", sequenceName = "seq_studentno2", allocationSize=1 , initialValue=10)
public class StudentEntity2 {


    @Id
    @Column(name="student2_no")
    @GeneratedValue(generator = "seqstudent2No", strategy = GenerationType.SEQUENCE)
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
    private List<StudentSubjectJoinEntity> subjects;

}
