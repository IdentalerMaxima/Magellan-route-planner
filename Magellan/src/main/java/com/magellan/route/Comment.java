package com.magellan.route;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "comment", nullable = false)
    private String comment;
    @Column(name = "routeId", nullable = false)
    private int routeId;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "rating", nullable = false)
    private int rating;

    public Comment() {
    }

    public Comment(String comment, int routeId, String userName, int rating) {
        this.comment = comment;
        this.routeId = routeId;
        this.userName = userName;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
