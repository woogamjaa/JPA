package com.jpa.main;

import com.jpa.common.JPATemplate;
import com.jpa.controller.BasicJpaController;
import com.jpa.controller.EmployeeController;
import com.jpa.controller.StudentController;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        //EntityManager객체 가져오기
        EntityManager entityManager = JPATemplate.getEntityManagerFactory()
                .createEntityManager();

StudentController studentController = new StudentController();
//        studentController.saveStudent(entityManager);
//        studentController.updateStudent(entityManager, 10L);
//        studentController.deleteStudent(entityManager, 10L);
//        studentController.findStudentByNo(entityManager, 10L);
//        studentController.oneToOneTest(entityManager);
//        studentController.OneToManyTest(entityManager);
//        studentController.oneToManySelect(entityManager,1L);
//        studentController.oneToManyTest2(entityManager);
          studentController.manyToManyTest(entityManager);

BasicJpaController controller = new BasicJpaController();
//        controller.basicTest(entityManager);
//        controller.searchTest(entityManager);
//        controller.insertMember(entityManager);
//        controller.selectMember(entityManager);
//        controller.insertMember2(entityManager);
//        controller.selectMember2(entityManager,3L); //시퀀스 캐시 때문에 21 . 기본적으로 20개가 캐시값이 주어지고 NO캐시하면 안 주어짐

//        EmployeeController c = new EmployeeController();
//          c.insertEmployeeDept(entityManager);
//          c.findEmployee(entityManager, 1L);
//          c.findDepartment(entityManager, 1L);

    }
}
