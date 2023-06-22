package com.example;

import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void 파라미터로_전달_받은_작성자명과_일치하는_유저_목록_조회() {

        //given
        String userName = "홍길동";

        //when
        String jpql = "SELECT u FROM user_jpql u WHERE userName=:writer";
        List<User> userList = entityManager.createQuery(jpql, User.class).setParameter("writer", userName).getResultList();

        //then
        assertNotNull(userList);
        userList.forEach(System.out::println);
    }

    @Test
    public void 게시글을_작성한_적이_있는_유저_목록_조회() {

        //when
        String jpql = "SELECT DISTINCT b.user FROM board_jpql b";
        List<User> userList = entityManager.createQuery(jpql, User.class).getResultList();

        //then
        assertNotNull(userList);
        userList.forEach(System.out::println);

    }

    @Test
    public void 파라미터로_전달_받은_단어가_포함되는_게시글_조회() {

        //given
        String keyword = "대";

        //when
        String jpql = "SELECT b FROM board_jpql b WHERE b.content LIKE '%' || :keyword || '%'";
        List<Board> boardList = entityManager.createQuery(jpql, Board.class)
                .setParameter("keyword", keyword)
                .getResultList();

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }


}
