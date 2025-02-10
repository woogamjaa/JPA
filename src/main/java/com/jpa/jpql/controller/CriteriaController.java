package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;


public class CriteriaController {
    public void basicCriteria(EntityManager em) {
        //빌더 생성하기
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        //쿼리문 생성
        CriteriaQuery<BoardEntity> criteriaQuery = criteriaBuilder.createQuery(BoardEntity.class);

        //조회대상 entity를 설정
        Root<BoardEntity> root = criteriaQuery.from(BoardEntity.class);

        //select문 작성 (select문 뒤이 들어갈 컬럼들 이야기하는 겁니다. )
        criteriaQuery.select(root); // criteriaQuery.getRoots();
        //select b from board d 와 같은 방법.

         TypedQuery<BoardEntity> tquery= em.createQuery(criteriaQuery);
         for(BoardEntity boardEntity : tquery.getResultList()) {
             System.out.println(boardEntity);
         }

         //반환되는 타입이 정해지지 않았을때
         //Object
         CriteriaQuery<Object> objectCriteriaQuery = criteriaBuilder.createQuery(Object.class);
         Root<BoardEntity> root2=objectCriteriaQuery.from(BoardEntity.class);
         objectCriteriaQuery.select(root2.get("boardTitle"));
         em.createQuery(objectCriteriaQuery);
         Query query = em.createQuery(objectCriteriaQuery);
         query.getResultList().forEach(System.out::println);

         //다수컬럼 선택하기
         //Object[]로 반환함.
         //게시글 제목, 게시글 내용
        CriteriaQuery<Object[]> objectArrCriteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BoardEntity> root3=objectArrCriteriaQuery.from(BoardEntity.class);
        objectArrCriteriaQuery.multiselect(root3.get("boardTitle"),root3.get("boardContent"));
        TypedQuery<Object[]> query2=em.createQuery(objectArrCriteriaQuery);
        query2.getResultList().forEach(arr-> {
            System.out.println(Arrays.toString(arr));
        });

        //select메소드를 이용해서 다수의 컬럼을 지정하기.
        //CriteriaBuilder객체가 제공하는 array()메소드를 이용하면
        //select로 다수 컬럼을 지정할 수 있다.
        Root<BoardEntity> root4=objectArrCriteriaQuery.from(BoardEntity.class);
        objectArrCriteriaQuery.select(
                criteriaBuilder.array(root4.get("boardReadcount"),root4.get("boardWriter")));

        query2=em.createQuery(objectArrCriteriaQuery);
        query2.getResultList().forEach(b-> {
            System.out.println(b[0]+" "+b[1]);
        });
    }

    public void criteriaWhere(EntityManager em) {
        //where절 사용하기
        //criteriabuilder객체가 제공하는 where절 함수를 이용
        //대소비교 : greaterThan(), greaterThanOrEquals(), lessThan(), lessThanEqualsTo()
        //숫자형 : gt(), lt(), ge() , le()
        //동등비교 : equal(), like(), in()
        //논리연산 : and(), or()
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<BoardEntity> criteriaQuery = cb.createQuery(BoardEntity.class);
        Root<BoardEntity> board = criteriaQuery.from(BoardEntity.class);
        criteriaQuery.select(board);

        //where절 작성하기
        Predicate where1 = cb.equal(board.get("boardWriter").get("userId"),"admin"); //객체다 보니까 두번 접근해서 가야겠죠.
//        criteriaQuery.where(where1);
        //select b from board b where b.board.userId='admin' 와 같음.

        //대소비교하기.
        Predicate where2 = cb.greaterThan(board.get("boardDate"), Date.valueOf(LocalDate.of(2019,04,01)));  //날짜에 대해서 더 크냐~? 라는.
//        criteriaQuery.where(where2);

        //초과
        Predicate where3=cb.gt(board.get("boardReadcount"),10);
        criteriaQuery.where(where3);

        //논리연산 메소드 사용하기.
//        Predicate totalwhere = cb.and(where1,where2,where3);
        Predicate totalwhere = cb.or(where1,where2,where3);
        criteriaQuery.where(totalwhere);

        TypedQuery<BoardEntity> tQuery=em.createQuery(criteriaQuery);
        tQuery.getResultList().forEach(System.out::println);


        //파라미터로 데이터 처리하기
        //criteriabuilder.parameter() 메소드를 이용
        CriteriaQuery<BoardEntity> criteriaQuery1= cb.createQuery(BoardEntity.class);



    }
}
