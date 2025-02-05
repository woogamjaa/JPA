package com.jpa.model.entity;

import com.jpa.common.Gender;
import com.jpa.common.Role;
import com.jpa.model.entity.dto.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity//(name="memberEntity") 엔티티명을 정할 수 있음

//Table 레벨에서 다수 대상으로 유니크 조건을 걸 수있음.
@Table(name="MEMBER_ENTITY" ,
    uniqueConstraints = {@UniqueConstraint(name="uq_memberId_memberName", columnNames = {"member_id","membmerName"})},
            indexes = {@Index(name="idx_memberId", columnList = "member_id,phone")
})//테이블 관련 설정을 하는 어노테이션 //필드하나에 걸었는데 2,3개 대상으로 유니크를 걸때.
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

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date accessLog;

    @Embedded
    private Address address;

    @Transient
    private String test; //DB랑 연동하는 필드가 아니다 라고 함.

    @Transient
    private List<String> names;

    @Lob //clob
    private String description;

    @Lob //blob 정말 작은용량의 데이터 ex) 서명같은거.
    private Byte[] bytes;

    //sql문으로 컬럼을 만드는 구문을 작성
    @Column(columnDefinition = "varchar2(20) default '없음' not null ")
    private String phone="없음";

}
