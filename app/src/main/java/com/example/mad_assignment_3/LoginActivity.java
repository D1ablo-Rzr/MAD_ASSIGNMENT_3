package com.example.mad_assignment_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_assignment_3.API.BackendImpl;
import com.example.mad_assignment_3.model.LoginResponse;
import com.example.mad_assignment_3.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edt,edt1;
    TextView txt;
    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        btn = findViewById(R.id.BtnSignUp);
        edt = findViewById(R.id.email);
        btn2 = findViewById(R.id.map);
        edt1 = findViewById(R.id.password);
        btn.setOnClickListener(view ->{
            Intent i = new Intent(this, RegistrationActivity.class);
            startActivity(i);

        } );


        Button btn1 = findViewById(R.id.button_login);
        btn1.setOnClickListener(v -> login());
        Bundle bnd = getIntent().getExtras();
        if (bnd != null)
        {
            EditText edt = findViewById(R.id.email);
            EditText edt1 = findViewById(R.id.password);
            String uname = bnd.getString("Email");
            String psd = bnd.getString("Password");
            edt.setText(uname);
            edt1.setText(psd);
        }
    }
    private void login() {
        String password = edt1.getText().toString();
        String email = edt.getText().toString();
            Call<LoginResponse> call = new BackendImpl(getApplicationContext()).login(email, password);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    LoginResponse data = response.body();
                    try {
                        if (data.status) {
                            Intent intent= new Intent(getApplicationContext(),MainFragment.class);
                            startActivity(intent);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(
                                getApplicationContext(),
                                "Enter a Valid Email",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not Login",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
    }
}