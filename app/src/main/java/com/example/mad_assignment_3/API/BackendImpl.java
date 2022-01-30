package com.example.mad_assignment_3.API;

import android.content.Context;

import com.example.mad_assignment_3.model.CompanyResponse;
import com.example.mad_assignment_3.model.LoginRequest;
import com.example.mad_assignment_3.model.LoginResponse;
import com.example.mad_assignment_3.model.RegisterRequest;
import com.example.mad_assignment_3.model.RegisterResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;

public class BackendImpl implements IBackend {
    ExecutorService executorService;
    Context context;

    public BackendImpl(Context context) {
        this.context = context;
        this.executorService = Executors.newCachedThreadPool();
    }

    public Call<LoginResponse> login(String username, String password) {
        return new Client().client
                .create(ApiInterface.class)
                .login(new LoginRequest(username, password));
    }

    public Call<RegisterResponse> register(String name, String password, String email) {
        return new Client().client
                .create(ApiInterface.class)
                .register(new RegisterRequest(name, password, email));
    }

    public Call<CompanyResponse> companies() {
        String token=getToken();
        return new Client().client.
                create(ApiInterface.class).
                getCompanies("Bearer " + token);
    }

    public void saveToken(String token) {
        context.getApplicationContext()
                .getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply();
    }

    public String getToken() {
        String token = context.getApplicationContext()
                .getSharedPreferences("auth", Context.MODE_PRIVATE)
                .getString("token", "");
        return token != null ? token : "";
    }
}
