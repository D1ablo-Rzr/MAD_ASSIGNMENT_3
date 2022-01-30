package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    public LoginRequest(String email, String password){
        this.email=email;
        this.password=password;
    }
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
}
