package com.jpa.controller;

import com.jpa.common.Gender;
import com.jpa.model.entity.ClassRoomEntity;
import com.jpa.model.entity.LockerEntity;
import com.jpa.model.entity.StudentEntity;
import com.jpa.model.entity.dto.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentController {

    public void saveStudent(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        StudentEntity student= StudentEntity.builder()
                .studentName("이민영")
                .grade(1)
                .classNum(1)
                .gender(Gender.F)
//                .birthday(new Date())
//                .address(Address.builder()
//                        .dong("수리동")
//                        .gungu("군포시")
//                        .sido("경기도")
//                        .zipCode("15869").build())
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
//        s.setAddress(Address.builder()
//                        .dong("부개동")
//                        .gungu("남동구")
//                        .sido("인천광역시")
//                        .zipCode("12345").build());
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

        StudentEntity s= em.find(StudentEntity.class,31L); //16번 학생을 조회하는거.
    }

    //OneToOne관계 엔티티 이용하기
    public void oneToOneTest(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        LockerEntity locker1 = LockerEntity.builder()
                .color("마젠타")
                .level("11층")
                .size("L")
                .build();
        em.persist(locker1);

        StudentEntity s= StudentEntity.builder()
                .studentName("아무개")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
//                .address(Address.builder().build())
//                .birthday(new Date())
                .locker(locker1)
                .build();
        em.persist(s);
        et.commit();
    }

    //단반향
    public void OneToManyTest(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<StudentEntity> students=new ArrayList<>();
        StudentEntity s=StudentEntity.builder()
                .studentName("이예진")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .build();
        em.persist(s);
        StudentEntity s1=StudentEntity.builder()
                .studentName("이민영")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .build();
        em.persist(s1);
        StudentEntity s2=StudentEntity.builder()
                .studentName("우민혁")
                .grade(1)
                .classNum(3)
                .gender(Gender.M)
                .build();
        em.persist(s2);
        StudentEntity s3=StudentEntity.builder()
                .studentName("정다인")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .build();
        em.persist(s3);

        students.add(s);
        students.add(s1);
        students.add(s2);
        students.add(s3);

        //캐스페이드 펄시스트.?

        ClassRoomEntity classRoomEntity= ClassRoomEntity.builder()
                .students(students)
                .classroomLevel("11층")
                .classroomName("풀스택8기")
                .build();

        em.persist(classRoomEntity);

        et.commit();

        ClassRoomEntity classRoom=em.find(ClassRoomEntity.class,1L);
        System.out.println(classRoom);
    }

    public void oneToManySelect(EntityManager em,Long pk) {
//      ClassRoomEntity classRoom=em.find(ClassRoomEntity.class,pk);
//      System.out.println(classRoom);
//      classRoom.getStudents().stream().forEach(System.out::println);



        //양방향
        StudentEntity student=em.find(StudentEntity.class,12L);
        System.out.println(student);
        //student.get
    }



    public void oneToManyTest2(EntityManager em) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        ClassRoomEntity room= ClassRoomEntity.builder()
                .classroomName("웹개발")
                .classroomLevel("10층")
                .build();

        StudentEntity s=StudentEntity.builder()
                .studentName("이예진")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .classroom(room)
                .build();
        em.persist(s);

        StudentEntity s1=StudentEntity.builder()
                .studentName("이민영")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .classroom(room)
                .build();
        em.persist(s1);

        StudentEntity s2=StudentEntity.builder()
                .studentName("우민혁")
                .grade(1)
                .classNum(3)
                .gender(Gender.M)
                .classroom(room)
                .build();
        em.persist(s2);

        StudentEntity s3=StudentEntity.builder()
                .studentName("정다인")
                .grade(1)
                .classNum(3)
                .gender(Gender.F)
                .classroom(room)
                .build();
        em.persist(s3);

        em.persist(room);
        et.commit();

        em.clear();
        StudentEntity findStudent=em.find(StudentEntity.class,12L);
        System.out.println(findStudent);
        System.out.println(findStudent.getClassroom().getClassroomName());

        ClassRoomEntity findRoom=em.find(ClassRoomEntity.class,1L);
        findRoom.getStudents().stream().forEach(System.out::println);
    }
}
