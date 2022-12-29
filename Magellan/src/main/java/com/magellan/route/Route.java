package com.magellan.route;

import jakarta.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int length;
    private int duration;
    private int elevation;
    private String images;
    private String description;
    private String routeDescription;
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    public Route() { }

    public Route(int id, String name, int length, int duration, int elevation, String images, String description, String routeDescription, DifficultyLevel difficultyLevel) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.duration = duration;
        this.elevation = elevation;
        this.images = images;
        this.description = description;
        this.routeDescription = routeDescription;
        this.difficultyLevel = difficultyLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
