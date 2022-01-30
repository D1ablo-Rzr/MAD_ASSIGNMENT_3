package com.example.mad_assignment_3.API;

import com.example.mad_assignment_3.model.CompanyResponse;
import com.example.mad_assignment_3.model.LoginResponse;
import com.example.mad_assignment_3.model.RegisterResponse;

import retrofit2.Call;

public interface IBackend {

    Call<LoginResponse> login(String username, String password);

    Call<RegisterResponse> register(String name, String password, String email);

    Call<CompanyResponse> companies();

    void saveToken(String token);

    String getToken();
}
