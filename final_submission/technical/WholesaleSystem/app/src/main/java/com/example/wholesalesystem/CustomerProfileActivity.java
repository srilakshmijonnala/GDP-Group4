package com.example.wholesalesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerProfileActivity extends AppCompatActivity {
    TextView name, phone, email, address,pass;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.stadmin));


        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        pass = findViewById(R.id.password);
        logout = findViewById(R.id.ivLogout);

        FirebaseFirestore.getInstance().collection("Customers").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(d -> {

            if(d.exists()){
                name.setText(":  "+d.getString("username"));
                email.setText(":  "+d.getString("Email"));
                phone.setText(":  "+d.getString("Phone"));
                address.setText(":  "+d.getString("Address"));
                pass.setText(":  "+d.getString("Password"));
            }
        });
        findViewById(R.id.ivEdit).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ProfileEditActivity.class)));
        logout.setOnClickListener(view -> logout());
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}