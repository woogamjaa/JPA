package com.jpa.model.복합키entity;


//복합키매핑클래스는 조건
//1. public 클래스로 설정
//2. 기본생성자가 반드시 있어야함.
//3. Serializable 인터페이스 구현체로 설정.
//4. equals(), hashcode() 오버라이딩해야함.
//선언된 필드명은 @Id가 설정된 필드명과 동일 해야함

import lombok.Data;

import java.io.Serializable;

@Data
public class CompositeKeyMapping implements Serializable {
    private Long firstId;
    private Long secondId;

}
