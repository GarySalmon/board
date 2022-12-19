package com.lee.boardsystem.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Msg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "publish")
    private Timestamp publish;


    @Column(name = "deadline")
    private Timestamp deadline;


//    @ManyToOne
    @Column(name = "user_id")
    private int user_id;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
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

    public Timestamp getPublish() {
        return publish;
    }

    public void setPublish(Timestamp publish) {
        this.publish = publish;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
}
