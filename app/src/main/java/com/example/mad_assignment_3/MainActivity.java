package com.example.mad_assignment_3;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button b1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.textView);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(view -> RegistrationActivity());
    }

        public  void RegistrationActivity()
        {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        }
}