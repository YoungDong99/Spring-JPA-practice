package com.example;

import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Tests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }
    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }
    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }
    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 테스트_메소드() {
        Professor professor = new Professor();
        professor.setProCode(51);
        professor.setProName("우별림");
        professor.setDeptCode("15");
        professor.setNumber("010-0000-0000");
        professor.setEmail("~~~~~~~~~~");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(professor);
        entityTransaction.commit();


    }
}
