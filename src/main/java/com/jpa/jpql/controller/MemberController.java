package com.jpa.jpql.controller;

import com.jpa.jpql.entity.BoardEntity;
import com.jpa.jpql.entity.WebMemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MemberController {

    public void selectMember(EntityManager em) {
        String jsql="select m from member m";

        jsql="select m.userName from member m";
        Query query1=em.createQuery(jsql);
        System.out.println("=======이름 조회=======");
        List<Object> memberList=query1.getResultList();
        memberList.forEach(System.out::println);
    }


    public void selectMember2(EntityManager em) {
        String jsql="select m from member m";

        jsql="select m.userId, m.password from member m";
        Query query1=em.createQuery(jsql);
        System.out.println("=======아이디 , pw 조회=======");
        List<Object[]> memberList1=query1.getResultList();
        memberList1.forEach(row -> {
            System.out.println(row[0]+""+row[1]);
        });

    }

    public void kimSelect(EntityManager em) {
        String jsql="select m from member m";
        TypedQuery<WebMemberEntity> tqueryParm=em.createQuery(jsql,WebMemberEntity.class);
        jsql="select m from member m where m.userName like :name";

        tqueryParm=em.createQuery(jsql,WebMemberEntity.class);
        tqueryParm.setParameter("name","%김%");
        System.out.println("======= kim 조회=======");
        tqueryParm.getResultList().forEach(System.out::println);

    }

    public void eunSelect(EntityManager em) {
        String jsql="select m from member m";
        TypedQuery<WebMemberEntity> tqueryParm1=em.createQuery(jsql,WebMemberEntity.class);
        jsql="select m from member m where m.userName like :name and m.gender= :gender";
        tqueryParm1=em.createQuery(jsql,WebMemberEntity.class);
        tqueryParm1.setParameter("name", "%은%");
        tqueryParm1.setParameter("gender", "F");
        tqueryParm1.getResultList().forEach(System.out::println);
    }

    public void memberJoinBoard(EntityManager em) {
        String jsql="select m from member m";

        jsql= """
                    select m,b
                    from board b join b.boardWriter m
                """;

        Query query1=em.createQuery(jsql);
        List<Object[]> resultList=query1.getResultList();
        resultList.forEach(e -> {
            Arrays.stream(e).forEach(System.out::println);
        });
    }



    //criterapi 1
    public void nameSelectApi(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Root<WebMemberEntity> root = cq.from(WebMemberEntity.class);
        cq.select(root.get("userName"));
        em.createQuery(cq);

        Query q = em.createQuery(cq);
        q.getResultList().forEach(System.out::println);
    }



}
