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
    @JoinTable(name = "subject_student",
            joinColumns = @JoinColumn(name = "subject_ref"),
            inverseJoinColumns = @JoinColumn(name = "student_ref"))
    private List<StudentEntity> studentList = new ArrayList<>();


    //양뱡향이니까 studentList / subjectList 에도 넣어야 한다.
    //1. 근데 이전 studentList 값을 지워줘야 한다. 음 못적음.
    public void setStudentList(List<StudentEntity> students) {
        if (students != null && students.size() > 0) {
            if (studentList.size() > 0) {
                //기존에 등록된 학생엔티티에서 과목을 삭제
                for (StudentEntity student : studentList) {
                    student.getSubjectList().remove(this);
                }
            }
            students.forEach(student -> {
                student.getSubjectList().add(this);
            });
            this.studentList = studentList;

        } else {
            throw new IllegalArgumentException("잘못된 매개변수가 전달됨");
        }
    }
}
