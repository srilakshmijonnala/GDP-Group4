package com.example.wholesalesystem.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wholesalesystem.LoginActivity;
import com.example.wholesalesystem.R;

public class AdminMainActivity extends AppCompatActivity {

    Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.stadmin));

  signOutBtn = findViewById(R.id.signOutBtn);

        signOutBtn.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));

        });

        findViewById(R.id.product).setOnClickListener(view -> {
            startActivity(new Intent(AdminMainActivity.this, ProductActivity.class));
        });
    }
}