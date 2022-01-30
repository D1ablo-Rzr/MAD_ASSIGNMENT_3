package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    public RegisterRequest(String name, String password, String email){
        this.email=email;
        this.name=name;
        this.password=password;
    }
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("name")
    String name;
}
