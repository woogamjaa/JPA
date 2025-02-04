package com.jpa.model.entity;

import com.jpa.common.Gender;
import com.jpa.common.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity//(name="memberEntity") 엔티티명을 정할 수 있음
@Table(name="MEMBER_ENTITY")//테이블 관련 설정을 하는 어노테이션
@SequenceGenerator(name="seqMemberEntityNo",sequenceName = "seq_memberentity_no",allocationSize = 1, initialValue = 1)        //sequence객체를 생성하는 어노테이션 구문들
public class MemberEntity {

    @Id
//    @Column(name="member_no")
    @GeneratedValue(generator = "seqMemberEntityNo", strategy = GenerationType.SEQUENCE)
    private Long memberNo;

    @Column(name="member_id", nullable=false, unique=true) // notnull 제약조건, unique 제약조건
    private String memberId;

    private String memberPwd;

    @Column(name="age")
    private Integer memberAge;

    @Column(length=20)
    private String memberName;

    @Column(name="gender")
    @Enumerated(EnumType.ORDINAL)//번호로 저장
    private Gender memberGender;

    @Enumerated(EnumType.STRING)
    private Role role;

}
