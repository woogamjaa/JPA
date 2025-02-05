package com.jpa.controller;

import com.jpa.common.Gender;
import com.jpa.common.Role;
import com.jpa.model.entity.MemberEntity;
import com.jpa.model.entity.SampleEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Date;

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
                .id(5L)
                .data("ㅇ어ㅘ 신기해 ! ")
                .build();

        sample2.setData("히히히 수정");
        
        em.persist(sample2); // <persist 를 진행해야만 인서트가 들어감.

        et.commit(); // 영속성 컨텍스트가 가지고 있는 sql문을 실행.


    }

        public void searchTest(EntityManager em) {
            EntityTransaction et = em.getTransaction();
            et.begin();

            SampleEntity sample = em.find(SampleEntity.class, 1L);
            System.out.println(sample);
            sample.setData("내가 수정한 것");

            em.persist(sample);

            et.commit();
        }


        public void insertMember(EntityManager em) {
            EntityTransaction et = em.getTransaction();

            et.begin();

            MemberEntity m=MemberEntity.builder() //한개 row를 만들었다.

                    .memberId("admin")
                    .memberPwd("1234")
                    .memberName("관리자")
                    .memberAge(19)
                    .memberGender(Gender.F)
                    .role(Role.ADMIN)
                    .build();

            em.persist(m); //영속화 콘테스트에 올라가면서 DB 관리할 수 있게 처리한다.
            et.commit();
        }


        public void selectMember(EntityManager em) {
        MemberEntity member=em.find(MemberEntity.class, 1L);
        System.out.println(member);
        }

        public void insertMember2(EntityManager em) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            MemberEntity m=MemberEntity.builder()
                    .memberId("user01")
                    .memberPwd("123456")
                    .memberName("유저1")
                    .memberAge(20)
                    .memberGender(Gender.M)
                    .role(Role.USER)
                    .accessLog(new Date())
                    .birthDay(new Date())
                    .build();

            em.persist(m);
            et.commit();
        }

    public void selectMember2(EntityManager em, Long no) {
        MemberEntity m=em.find(MemberEntity.class, no);
        System.out.println(m);
    }
}
