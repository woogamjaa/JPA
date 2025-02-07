package com.jpa.model.복합키entity;


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
@Table(name="student_subject_join2" , uniqueConstraints = {
        @UniqueConstraint(name="uq_student_subject2", columnNames = {"student_ref","subject_ref"})
})
@SequenceGenerator(name="seqstudentsubjectjoinNo2", sequenceName = "seq_studentsubjectjoin_no2", allocationSize = 1)

@IdClass(StudentSubjectKeyMapping.class)
public class StudentSubjectJoinEntity2 {
//    @Id
//    @GeneratedValue(generator = "seqstudentsubjectjoinNo2", strategy = GenerationType.SEQUENCE)
//    private Long studentSubjectJoinNo;

    private String year;
    private String term;

    @Id //복합키.
    @ManyToOne
    @JoinColumn(name="student_ref", nullable=false)
    private StudentEntity3 student;

    @Id
    @ManyToOne
    @JoinColumn(name="subject_ref", nullable=false)
    private SubjectEntity3 subject;



}
