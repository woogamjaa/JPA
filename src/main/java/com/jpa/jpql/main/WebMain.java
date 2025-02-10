package com.jpa.jpql.main;

import com.jpa.common.JPATemplate;
import com.jpa.jpql.controller.CriteriaController;
import com.jpa.jpql.controller.MemberController;
import com.jpa.jpql.controller.WebController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.Map;

public class WebMain {
    public static void main(String[] args) {


        EntityManager em = JPATemplate.getWebEntityFactory().createEntityManager();
        WebController wc = new WebController();
//        wc.basicJPQL(em);
//        wc.useWhere(em);
//        wc.groupByFunction(em);
//        wc.paginationTest(em);
//        wc.joinTest(em);

        CriteriaController cc= new CriteriaController();
//        cc.basicCriteria(em);
//        cc.criteriaWhere(em);
//        cc.orderTest(em);
//          cc.joinTest(em);
//          cc.dynamicQuery(em, Map.of("title","안녕","writer","admin"));
//          cc.dynamicQuery(em, Map.of("title","안녕"));
//          cc.dynamicQuery(em, Map.of("writer","abcde","content","게시"));

//        em.close();

        MemberController mc = new MemberController();
        //jpql
//        mc.selectMember(em);
//        mc.selectMember2(em);
//        mc.KimSelect(em);
//        mc.eunSelect(em);
//        mc.memberJoinBoard(em);

        //criteriaApi
        mc.nameSelectApi(em);
        em.close();
    }
}
