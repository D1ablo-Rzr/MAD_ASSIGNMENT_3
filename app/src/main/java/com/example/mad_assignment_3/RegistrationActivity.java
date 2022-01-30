package com.example.mad_assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mad_assignment_3.API.BackendImpl;
import com.example.mad_assignment_3.model.LoginResponse;
import com.example.mad_assignment_3.model.RegisterResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText e_name, e_email, e_password, e_re_password;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e_name = findViewById(R.id.inputUsername);
        e_email = findViewById(R.id.email);
        e_password = findViewById(R.id.password);
        e_re_password = findViewById(R.id.repassword);
        b1=findViewById(R.id.sigintext);
        b1.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        });
        Button register = findViewById(R.id.regButton);

        register.setOnClickListener(v -> {
            String password = e_password.getText().toString();
            if(validationName() && validationEmail() && validationPassword() && validationRe_password(password))
            {
                register();
            }
        });

    }

    private boolean validationName()
    {
        String val = e_name.getText().toString();
        if (val.isEmpty())
        {
            e_name.setError("Field cannot be empty");
            return false;
        }
        else {
            e_name.setError(null);
            return true;
        }
    }

    private boolean validationEmail()
    {
        String val = e_email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty())
        {
            e_email.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches((emailPattern)))
        {
            e_email.setError("Your Email Is not Incorrect");
            return false;
        }
        else
            {
            e_email.setError(null);
            return true;
        }
    }

    private boolean validationPassword()
    {
        String val = e_password.getText().toString();
        String  pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (val.isEmpty())
        {
            e_password.setError("Field cannot be empty");
        }
            else if ( !val.matches((pattern)))
            {
                e_password.setError("Password is too weak");
           }

        else {
            e_password.setError(null);
            return true;
        }
        return false;
    }

    private boolean validationRe_password(String pass)
    {
        String val = e_re_password.getText().toString();
        if (val.isEmpty())
        {
            e_re_password.setError("Field can not be empty");
            return false;
        }
        if (!pass.equals(val))
        {
            e_re_password.setError("Password Does not Match");
            return false;
        }
        e_re_password.setError(null);
        return true;
    }

    private void register() {
        String password = e_re_password.getText().toString();
        String username = e_name.getText().toString();
        String email = e_email.getText().toString();

        new Thread(() -> {
            Call<RegisterResponse> call = new BackendImpl(getApplicationContext()).register(username, password, email);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse data = response.body();
                    if (data.status) {
                        Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Could not register",
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }).start();
    }
}