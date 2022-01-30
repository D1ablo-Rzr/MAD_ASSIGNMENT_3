package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse extends Response {
    @SerializedName("token")
    public String token;
}
