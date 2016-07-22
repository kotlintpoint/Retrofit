package com.example.ankitsodha.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ankit Sodha on 22-Jul-16.
 */
public class UserResponse {

    @SerializedName("data")
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
