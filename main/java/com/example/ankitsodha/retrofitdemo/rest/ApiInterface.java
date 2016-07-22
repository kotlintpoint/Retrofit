package com.example.ankitsodha.retrofitdemo.rest;

import com.example.ankitsodha.retrofitdemo.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ankit Sodha on 22-Jul-16.
 */
public interface ApiInterface {

    @GET("myservice.php")
    Call<UserResponse> getAllUser(@Query("select") String select);

    @GET("myservice.php?insert")
    Call<String> insertUser(@Query("username") String username, @Query("password") String password);
}
