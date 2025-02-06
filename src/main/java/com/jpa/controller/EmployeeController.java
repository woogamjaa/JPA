package com.jpa.controller;


import com.jpa.model.entity.DepartmentEntity;
import com.jpa.model.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class EmployeeController {

    public void insertEmployeeDept(EntityManager em) {
        EntityTransaction et=em.getTransaction();
        et.begin();

        DepartmentEntity d= DepartmentEntity.builder()
                .departmentName("개발팀")
                .build();
        em.persist(d);

        DepartmentEntity d1= DepartmentEntity.builder()
                .departmentName("경영팀")
                .build();
        em.persist(d1);

        EmployeeEntity e= EmployeeEntity.builder()
                .employeeName("유병승")
                .age(19)
                .address("경기도시흥시")
                .salary(100)
                .department(d)
                .build();
        em.persist(e);

        EmployeeEntity e1= EmployeeEntity.builder()
                .employeeName("이민영")
                .age(19)
                .address("경기도군포시")
                .salary(200)
                .department(d)
                .build();
        em.persist(e1);

        EmployeeEntity e2= EmployeeEntity.builder()
                .employeeName("염상균")
                .age(19)
                .address("경기도안양시")
                .salary(150)
                .department(d1)
                .build();
        em.persist(e2);

        et.commit();

    }

    public void findEmployee(EntityManager em , Long pk) {
        em.clear();
        EmployeeEntity e= em.find(EmployeeEntity.class, pk);
        System.out.println(e);
    }
    public void findDepartment(EntityManager em, Long pk) {
        em.clear();
        DepartmentEntity d= em.find(DepartmentEntity.class, pk);
        System.out.println(d);

    }

}
