package com.jpa.common;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPATemplate {
    private static EntityManagerFactory emf;
    private JPATemplate() {}
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
           emf=Persistence.createEntityManagerFactory("basicjpa"); {
           return emf;
        }
    }
}
