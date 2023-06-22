package com.example;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NativeQueryTests {
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
    public void 파라미터로_전달_받은_아이디와_일치하는_유저_조회() {

        //given
        int userId = 1;

        //when
        String query = "SELECT * FROM tbl_user WHERE user_id = ?";
        User foundUser = (User) entityManager.createNativeQuery(query, User.class)
                .setParameter(1, userId)
                .getSingleResult();

        //then
        assertNotNull(foundUser);
        System.out.println(foundUser);

    }

    @Test
    public void 파라미터로_전달_받은_기간에_게시된_게시글_목록_조회() {

        //given
        String startDate = "2023-06-18 00:00:00";
        String endDate = "2023-06-24 23:59:59";

        //when
        String query = "SELECT * FROM tbl_board WHERE created_date BETWEEN ? AND ?";
        List<Board> boardList = entityManager.createNativeQuery(query, Board.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .getResultList();

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);

    }

    @Test
    public void 유저_목록_조회시_해당_유저가_작성한_게시글_수_함께_조회() {

        //when
        String query = "SELECT u.user_id, u.user_name, COUNT(b.board_id) AS post_count " +
                "FROM tbl_user u " +
                "LEFT JOIN tbl_board b ON u.user_id = b.writer " +
                "GROUP BY u.user_id, u.user_name";

        List<Object[]> userList = entityManager.createNativeQuery(query).getResultList();

        //then
        assertNotNull(userList);
        userList.forEach(row -> {
            Stream.of(row).forEach(col -> System.out.print(col + " "));
            System.out.println();
        });

    }

    @Test
    public void 게시글_전체_목록을_조회하는_NamedNativeQuery() {
        // when
        Query nativeQuery = entityManager.createNamedQuery("Board.findAll");
        List<Board> boardList = nativeQuery.getResultList();

        // then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }

}
