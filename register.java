package com.example.wholesalesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

        private EditText etFullName, etEmail, etPassword;
        private Button btnRegister;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            etFullName = findViewById(R.id.etFullName);
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            btnRegister = findViewById(R.id.btnRegister);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve user input
                    String fullName = etFullName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString();

                    // TODO: Implement user registration logic, send data to the server
                    // Example: Call a registration API

                    // After successful registration, navigate to the login screen
                    Intent intent = new Intent(register.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

