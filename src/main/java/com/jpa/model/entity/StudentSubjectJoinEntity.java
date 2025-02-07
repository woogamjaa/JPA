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
@Table(name="student_subject_join" , uniqueConstraints = {
        @UniqueConstraint(name="uq_student_subject", columnNames = {"student_ref","subject_ref"})
})
@SequenceGenerator(name="seqstudentsubjectjoinNo", sequenceName = "seq_studentsubjectjoin_no", allocationSize = 1)
public class StudentSubjectJoinEntity {
    @Id
    @GeneratedValue(generator = "seqstudentsubjectjoinNo", strategy = GenerationType.SEQUENCE)
    private Long studentSubjectJoinNo;

    private String year;
    private String term;

    @ManyToOne
    @JoinColumn(name="student_ref", nullable=false)
    private StudentEntity2 student;

    @ManyToOne
    @JoinColumn(name="subject_ref", nullable=false)
    private SubjectEntity2 subject;



}
