package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
    }
}
