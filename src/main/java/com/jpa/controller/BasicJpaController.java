package com.jpa.controller;

import com.jpa.model.entity.SampleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BasicJpaController {
    public void basicTest(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin(); // 트렌젝션 시작

        //비영속 상태
        SampleEntity sample = SampleEntity.builder()
                .id(1L)
                .data("첫번째엔티티")
                .build();


        System.out.print(sample);
        em.persist(sample); //-> sample 객체를 영속화하기 -> jpa에 의해 관리


        SampleEntity sample2 = SampleEntity.builder()
                .id(2L)
                .data("ㅇ어ㅘ 신기해 ! ")
                .build();

        em.persist(sample2); // <persist 를 진행해야만 인서트가 들어감.

        et.commit(); // 영속성 컨텍스트가 가지고 있는 sql문을 실행.

    }
}
