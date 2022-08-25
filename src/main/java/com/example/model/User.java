package com.example.model;

import java.time.ZoneId;
import java.util.Locale;

public class User {

    private int userId;
    private String userName;
    private String password;

    private ZoneId userLocation;

    private Locale userLanguage;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZoneId getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(ZoneId userLocation) {
        this.userLocation = userLocation;
    }

    public Locale getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(Locale userLanguage) {
        this.userLanguage = userLanguage;
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(int userId, String userName, String password, ZoneId userLocation, Locale userLanguage) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userLocation = userLocation;
        this.userLanguage = userLanguage;
    }

    public User() {
    }

    @Override
    public String toString(){
        return ("#" + userId + " " + userName);
    }

}
