package com.example.ankitsodha.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ankit Sodha on 22-Jul-16.
 */
public class User {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public User(String username,String password)
    {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
