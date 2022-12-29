package com.magellan.user;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    public static List<User> loggedInUsers = new ArrayList<>();

    public User() {
    }

    public User(String username, Boolean isAdmin, String email, String password) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.email = email;
        this.password = password;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
