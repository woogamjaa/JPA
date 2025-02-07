package com.jpa.model.복합키entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

@Entity
@Table(name="subject_entity3")
@SequenceGenerator(name="seqSubject3No", sequenceName = "seq_subject3_no", allocationSize = 1)
public class SubjectEntity3 {

    @Id
    @GeneratedValue(generator = "seqSubject3No", strategy = GenerationType.SEQUENCE)
    private Long subjectNo;

    private String subjectName;
    private int subjectFee;

    @OneToMany(mappedBy = "subject")
    private List<StudentSubjectJoinEntity2> students;
}
