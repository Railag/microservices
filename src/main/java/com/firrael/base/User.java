package com.firrael.base;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String username;
    private String token;

    private String application;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> notifications;
    private long points;

    public User() {
    }

    public User(String name, String token, String application) {
        this.username = name;
        this.token = token;
        this.application = application;

        Random random = new Random();

        int id = random.nextInt(100);
        notifications = new ArrayList<>();
        notifications.add("You have been promoted!");
        notifications.add("You have been promoted!");
        notifications.add("Sorry, disregard the previous notification - wrong user");
        points = id * 31 % 1000;

        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}