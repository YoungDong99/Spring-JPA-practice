package com.example;

import org.junit.jupiter.api.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManager {

    private static EntityManagerFactory entityManagerFactory;
    private javax.persistence.EntityManager entityManager;
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
    public void 영속성_테스트_코드() {
        Order order = new Order();

        //새로운 order 엔티티를 영속 상태로 만든다.
        entityManager.persist(order);

        //저장했던 order 엔티티를 영속성 컨텍스트에서 분리하여 준영속 상태로 만든다.
        entityManager.detach(order);

        //준영속 상태의 엔티티를 다시 영속 상태로 만든다.
        entityManager.merge(order);

        //영속성 컨텍스트에서 제거하여 비영속 상태로 만든다.
        entityManager.remove(order);
    }

}
