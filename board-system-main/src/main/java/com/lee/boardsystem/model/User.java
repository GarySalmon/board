package com.lee.boardsystem.model;


import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

// db 變數
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

//    @OneToMany
//    private Set<Msg> msgs;

//    @OneToMany(mappedBy="user", cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,orphanRemoval = true)
//    public Set<Msg> getMsgs() {
//        return msgs;
//    }
//
//    public void setMsgs(Set<Msg> msgs) {
//        this.msgs = msgs;
//    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
