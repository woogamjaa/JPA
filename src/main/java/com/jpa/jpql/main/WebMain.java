package com.jpa.jpql.main;

import com.jpa.common.JPATemplate;
import com.jpa.jpql.controller.CriteriaController;
import com.jpa.jpql.controller.WebController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

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
        cc.basicCriteria(em);
        em.close();
    }
}
