package com.jpa.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity//(name="memberEntity") 엔티티명을 정할 수 있음

//Table 레벨에서 다수 대상으로 유니크 조건을 걸 수있음.
@Table(name="classroom")
@SequenceGenerator(name = "seqclassroom", sequenceName = "seq_classroomno")
public class ClassRoomEntity {

    @Id
    @GeneratedValue(generator = "seqclassroom", strategy = GenerationType.SEQUENCE)
    private Long classroomNo;

    private String classroomName;
    private String classroomLevel;

    //학생들
//    @OneToMany //단방향 설정.
//    private List<StudentEntity> students;


    @ToString.Exclude //json 때 문제 생길 수 있음.
    @OneToMany(mappedBy="classroom")//양방향 설정.
    private List<StudentEntity> students;

}
