package com.example;

import javax.persistence.*;
import java.util.Date;

@Entity(name="board_jpql")
@Table(name="tbl_board")
@NamedQuery(name = "Board.findAll", query = "SELECT b FROM board_jpql b")
public class Board {
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @JoinColumn(name="writer")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;
    @Column(name="created_date")
    private Date created_date;
    public Board() { }
    public Board(int boardId, String title, String content, User writer, Date created_date) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.created_date = created_date;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer=" + user +
                ", created_date=" + created_date +
                '}';
    }
}
