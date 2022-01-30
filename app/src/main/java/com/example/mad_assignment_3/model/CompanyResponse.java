package com.example.mad_assignment_3.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompanyResponse extends Response{
    @SerializedName("data")
    public ArrayList<Company> data;
}