package com.example.mad_assignment_3.API;

import com.example.mad_assignment_3.model.*;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/api/v1/login")
    public Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("/api/v1/register")
    public Call<RegisterResponse> register(@Body RegisterRequest registerResponse);
    @GET("/api/v1/getCompanies")
    public Call<CompanyResponse> getCompanies(@Header("Authorization") String token);
}

