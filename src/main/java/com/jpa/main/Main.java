package com.jpa.main;

import com.jpa.common.JPATemplate;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        //EntityManager객체 가져오기
        EntityManager entityManager = JPATemplate.getEntityManagerFactory()
                .createEntityManager();
    }
}
