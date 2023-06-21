package com.example.section01.manytoone;

import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

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
    public void 게시글_작성_테스트() {
        Board board = new Board();
        board.setTitle("Spring");
        board.setContent("다대일관계");
//        User user = new User();
//        user.setUserName("동영");

        board.setUser(entityManager.find(User.class, 1));
        board.setCreated_date(new Date());

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        //entityManager.persist(user);

        entityManager.persist(board);
        entityTransaction.commit();
    }

}
