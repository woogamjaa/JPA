package com.jpa.model.entity;

import com.jpa.common.Gender;
import com.jpa.model.entity.dto.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="STUDENT")
@SequenceGenerator(name="seqstudentNo", sequenceName = "seq_studentno", allocationSize=1 , initialValue=10)
public class StudentEntity {


    @Id
    @Column(name="student_no")
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

//    @Column(columnDefinition = "Date default SYSDATE")
//    @Temporal(TemporalType.DATE)
//    private Date birthday;

//    @Embedded
//    private Address address;


    @OneToOne
    @JoinColumn(name="locker_no", unique=true)
    private LockerEntity locker;

    @ManyToOne
    private ClassRoomEntity classroom; //포린티 컬럼이 들어가는건 다대일에 서 다 쪽이다.


    @ToString.Exclude
    @ManyToMany(mappedBy = "studentList")
    private List<SubjectEntity> subjectList=new ArrayList<>();

}
