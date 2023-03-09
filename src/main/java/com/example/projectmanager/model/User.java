package com.example.projectmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
    private List<Task> taskList;

    @Column(name = "role")
    private String role;

    @Column(name = "PASSWORD")
    private String password;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name) {
        this.username = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
}
