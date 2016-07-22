package com.example.ankitsodha.retrofitdemo.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ankit Sodha on 22-Jul-16.
 */
public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.29:8080/myproject/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
