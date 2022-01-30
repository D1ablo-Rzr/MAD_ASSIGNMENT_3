package com.example.mad_assignment_3.API;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    Retrofit client;
    Client() {
        this.client = new Retrofit.Builder()
                .baseUrl("https://c-company.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}
