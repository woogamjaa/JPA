package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

@Entity
@Table(name="subject_entity2")
@SequenceGenerator(name="seqSubject2No", sequenceName = "seq_subject2_no", allocationSize = 1)
public class SubjectEntity2 {

    @Id
    @GeneratedValue(generator = "seqSubject2No", strategy = GenerationType.SEQUENCE)
    private Long subjectNo;

    private String subjectName;
    private int subjectFee;

    @OneToMany(mappedBy = "subject")
    private List<StudentSubjectJoinEntity> students;
}
