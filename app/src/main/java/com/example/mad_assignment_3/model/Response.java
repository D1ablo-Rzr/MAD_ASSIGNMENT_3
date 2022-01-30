package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("status")
    public boolean status;
    @SerializedName("message")
    public String message;
}
