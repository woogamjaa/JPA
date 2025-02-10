package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WebController {

    public void basicJPQL(EntityManager em) {
        //JPQL이용하기

        //기본 SELECT문 작성하기
        //1. 대소문자를 구분함.
        //2. 대상이 엔티티객체

        String jsql="select b from board b";
        //entityManger객체가 제공하는 createQuery메소드로 쿼리문 생성201
        //첫번쨰 매개변수 : JPQL구문을 String타입으로 전달
        //두번째 매개변수(생략) : 반환하는 엔티팉입을 설정
        TypedQuery<BoardEntity> query =em.createQuery(jsql, BoardEntity.class);

        //결과를 가져오기
        //1. getResultList() : 가져온 엔티티를 List 로 반환
        //2. getSingleResult() : 한개 엔티티를 가져와 반환
        //3. getResultStream() : 가져온 엔티티를 stream으로 반환
        List<BoardEntity> boardList=query.getResultList();
        //System.out.println(boardList.size());

        //원하는 필드를 선택해서 조회하기
        jsql="select b.boardTitle from board b";
        Query query1=em.createQuery(jsql);
        List<Object> titleList= query1.getResultList();
        System.out.println("====제목만 출력하기====");
        titleList.forEach(System.out::println);

        //원하는 필드 다수로 선택해서 조회하기.
        jsql="select b.boardTitle, b.boardContent, b.boardDate from board b";
        query1=em.createQuery(jsql);
        List<Object[]> manycolumnList= query1.getResultList();
        manycolumnList.forEach(row-> {
            System.out.println(row[0]+""+row[1]+""+row[2]);
        });

        //선택한 컬럼으로 엔티티를 생성해서 반환
        //생성자를 만들어야 한다.
        jsql="""
             select
             NEW com.jpa.jpql.entity.BoardEntity(b.boardTitle, b.boardReadcount, b.boardDate) 
             from board b
             """;

        query=em.createQuery(jsql,BoardEntity.class);
        List<BoardEntity> constructorList=query.getResultList();
        System.out.println("생성자로 가져온 데이터");
        constructorList.forEach(System.out::println);

        query.getSingleResult();//row(엔티티)가 한개만 가져오는 것
        query.getResultStream()
                .filter(boardEntity -> boardEntity.getBoardReadcount()>5)
                .forEach(System.out::println);

    }

    public void useWhere(EntityManager em) {
        //JPQL에서 WHERE절 사용하기
        //표준 SQL과 동일하게 사용할 수 있음
        //WHERE절에 대상이 되는 항목은 엔티티의 필드로 설정
        String jsql="select b from board b where b.boardWriter.userId='admin'";
        TypedQuery<BoardEntity> tquery=em.createQuery(jsql,BoardEntity.class);
        System.out.println(tquery.getResultStream().count());

        //변수를 이용해서 where 사용하기
        Scanner sc=new Scanner(System.in);
        String id=sc.nextLine();
        jsql="select b from board b where b.boardWriter.userId='"+id+"'";
        tquery=em.createQuery(jsql,BoardEntity.class);
        tquery.getResultStream().forEach(System.out::println);


        //파라미터를 이용해서 처리하기
        //1. 인덱스로 값을 넣기 -> : ?인덱스 번호  1부터 시작. // 프리페어드스테이드먼트랑 비슷하죠? 1. 2 로 해서 넣었잖아요 .?
        //2. 이름으로 값을 넣기 -> : 이름

        //setParameter(인덱스번호||이름,대입할 값)메소드를 이용해서 값을 저장
        jsql="select b from board b where b.boardWriter.userId= ?1";
        TypedQuery<BoardEntity> tqueryParm=em.createQuery(jsql,BoardEntity.class);
//        tqueryParm.setParameter(1,"abced" );
        tqueryParm.setParameter(1, id);
        Long result=tquery.getResultList().stream().count();
        System.out.println(result);


        jsql="select b.boardTitle, b.boardContent from board b where b.boardWriter.userId=:id"; //key 를 설정한 것
        Query query2=em.createQuery(jsql);
        query2.setParameter("id","admin");
        List<Object[]> resultList=query2.getResultList();
        resultList.forEach(datas-> {
            System.out.println(Arrays.toString(datas));
        });



    }




}
