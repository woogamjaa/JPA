package com.jpa.controller;

import com.jpa.common.Gender;
import com.jpa.model.entity.StudentEntity;
import com.jpa.model.entity.dto.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.Date;

public class StudentController {

    public void saveStudent(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        StudentEntity student= StudentEntity.builder()
                .studentName("이민영")
                .grade(1)
                .classNum(1)
                .gender(Gender.F)
                .birthday(new Date())
                .address(Address.builder()
                        .dong("수리동")
                        .gungu("군포시")
                        .sido("경기도")
                        .zipCode("15869").build())
                .build();

        em.persist(student);
        et.commit();
    }

    public void updateStudent(EntityManager em,Long no) {
        StudentEntity s= em.find(StudentEntity.class,no);
        EntityTransaction et = em.getTransaction();
        et.begin();
        s.setGrade(2);
        s.setClassNum(4);
        s.setAddress(Address.builder()
                        .dong("부개동")
                        .gungu("남동구")
                        .sido("인천광역시")
                        .zipCode("12345").build());
        em.persist(s);
        et.commit();
    }

    public void deleteStudent(EntityManager em,Long no) {
        StudentEntity s= em.find(StudentEntity.class,no);
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(s);
        et.commit();
    }

    public void findStudentByNo(EntityManager em,Long no) {
        StudentEntity s= em.find(StudentEntity.class,10L);
        System.out.println(s);
    }

    //테이블에 있는 모든 row 가져오기 -> JPQL구문 사용
    public void findStudent(EntityManager em) {
        String jqpl="select s from StudentEntity s";
        TypedQuery<StudentEntity> query = em.createQuery(jqpl,StudentEntity.class);
        EntityTransaction et = em.getTransaction();
        et.begin();
        query.getResultList().forEach(studentEntity->{
            System.out.println("수정하기");
            System.out.println(studentEntity);
            studentEntity.setClassNum((int)(Math.random()*10)+1);
        });
        et.commit();

        StudentEntity s= em.find(StudentEntity.class,16L); //16번 학생을 조회하는거.
    }
}
