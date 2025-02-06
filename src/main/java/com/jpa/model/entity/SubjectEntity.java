package com.jpa.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name="subject")
@SequenceGenerator(name="seqSubjectNo", sequenceName = "seq_subject_no", allocationSize = 1)
public class SubjectEntity {

    @Id
    @GeneratedValue(generator = "seqSubjectNo", strategy = GenerationType.SEQUENCE)
    private Long subjectNo;

    private String subjectName;
    private int subjectFee;

    @ManyToMany
    //name:테이블이름
    //joinColimns : this entity의 fk로 참조할 컬럼설정
    //inverseJoinColumns : 상대방 entity의  fk로 참조할 컬럼설정 -> Id
    @JoinTable(name="subject_student",
            joinColumns = @JoinColumn(name="subject_ref"),
            inverseJoinColumns = @JoinColumn(name="student_ref"))
    private List<StudentEntity> studentList=new ArrayList<>();
}
