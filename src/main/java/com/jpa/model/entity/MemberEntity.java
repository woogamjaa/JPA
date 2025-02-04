package com.jpa.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity//(name="memberEntity") 엔티티명을 정할 수 있음
@Table(name="MEMBER_ENTITY")//테이블 관련 설정을 하는 어노테이션
@SequenceGenerator(name="seqMemberEntityNo",sequenceName = "seq_memberentity_no",allocationSize = 1, initialValue = 1)        //sequence객체를 생성하는 어노테이션 구문들
public class MemberEntity {

}
