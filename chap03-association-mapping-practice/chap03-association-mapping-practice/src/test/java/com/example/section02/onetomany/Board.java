package com.example.section02.onetomany;

import javax.persistence.*;
import java.util.Date;

@Entity(name="board_onetomany")
@Table(name="tbl_board")
public class Board {
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @JoinColumn(name="user_id")
    @Column(name="writer")
    private int writer;
    @Column(name="created_date")
    private Date created_date;
    public Board() { }
    public Board(int boardId, String title, String content, int writer, Date created_date) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
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

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
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
                ", writer=" + writer +
                ", created_date=" + created_date +
                '}';
    }
}
