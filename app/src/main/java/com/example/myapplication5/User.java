package com.example.myapplication5;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String account;
    private String password;
    private String userName;
    private String gender;
    private double height;
    private double weight;
    private String userImage;

    private String profileImagePath;
    public User(int userId, String account, String password, String userName, String gender, double height, double weight, String userImage) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.userImage = userImage;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
