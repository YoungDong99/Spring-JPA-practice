package com.example;

import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
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

    @Test
    public void BoardInfo_프로젝션_테스트() {

        //when
        String jpql = "SELECT new com.example.BoardInfo " +
                "(b.boardId,b.title,b.content) FROM board_jpql b";
        List<BoardInfo> boardInfoList = entityManager.createQuery(jpql, BoardInfo.class).getResultList();

        //then
        assertNotNull(boardInfoList);
        boardInfoList.forEach(System.out::println);
    }

    @Test
    public void 가장_컨텐츠의_길이가_긴_게시글_조회() {

        //when
        String jpql = "SELECT b " +
                      "FROM board_jpql b " +
                      "WHERE LENGTH(b.content) = (" +
                                                 "SELECT MAX(LENGTH(b2.content))" +
                                                 "FROM board_jpql b2" +
                                                 ")";
        List<Board> boardList = entityManager.createQuery(jpql, Board.class).getResultList();

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);

    }

    @Test
    public void 파라미터로_전달_받은_단어가_포함되는_게시글_조회시_유저_즉시로딩으로_수정() {

        //given
        String keyword = "칼국수";

        //when
        String jpql = "SELECT DISTINCT b FROM board_jpql b LEFT JOIN FETCH b.user WHERE b.content LIKE CONCAT('%', :keyword, '%')";
        List<Board> boardList = entityManager.createQuery(jpql, Board.class)
                .setParameter("keyword", keyword)
                .getResultList();

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }

    @Test
    public void 게시글_전체_목록_조회를_NamedQuery로_작성() {

        //when
        List<Board> boardList = entityManager.createNamedQuery("Board.findAll", Board.class).getResultList();

        //then
        assertNotNull(boardList);
        boardList.forEach(System.out::println);
    }

    @Test
    public void 데이터_추가_메소드() {
        // given
        String title = "오늘 점심";
        String content = "파인하우스 칼국수";
        int userId = 3;
        Date currentDate = new Date();

        User user = entityManager.find(User.class, userId);

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setUser(user);
        board.setCreated_date(currentDate);

        entityManager.getTransaction().begin();
        entityManager.persist(board);
        entityManager.getTransaction().commit();

        assertNotNull(board.getBoardId());
        System.out.println(board);
    }


}
