package com.example.mad_assignment_3.model;


import com.google.gson.annotations.SerializedName;

public class Company {
    public Company() {

    }
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("phone")
    public String phone;
    @SerializedName("logo")
    public String logo;
    @SerializedName("description")
    public String description;
    @SerializedName("geolocation")
    public Geolocation location;
}
