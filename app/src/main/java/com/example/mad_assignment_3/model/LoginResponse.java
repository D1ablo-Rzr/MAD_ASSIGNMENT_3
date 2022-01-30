package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse extends Response {
    @SerializedName("token")
    public String token;
    @SerializedName("user")
    public User user;
}
