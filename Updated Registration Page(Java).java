package com.example.wholesalesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class RegisterActivity extends AppCompatActivity {


    EditText username, email,phone, address, password, conpassword;

    FirebaseAuth fauth;

    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



            username = findViewById(R.id.registeruname);
            password = findViewById(R.id.registerpassword);
            email = findViewById(R.id.registeremail);
        phone = findViewById(R.id.registerphone);
        address = findViewById(R.id.registeraddress);
            conpassword = findViewById(R.id.conregisterpassword);
            fauth = FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();
            CardView register = findViewById(R.id.registerevent);

            findViewById(R.id.gotologin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(username.getText().length()==0 && password.getText().length()==0 && email.getText().length()==0 && phone.getText().length()==0 && address.getText().length()==0 && conpassword.getText().length()==0){
                        Toasty.warning(RegisterActivity.this, "All Fields Are Required!!", Toast.LENGTH_SHORT, true).show();
                      }else if((password.getText().toString().length()<=5)){
                        Toasty.warning(RegisterActivity.this, "Password Must be 6 Character.", Toast.LENGTH_SHORT, true).show();
                    }else if(!(password.getText().toString().equals(conpassword.getText().toString()))){
                        Toasty.warning(RegisterActivity.this, "Password & Confirm Password Must be Same. (" +password.getText().toString()+") & ("+conpassword.getText().toString()+")", Toast.LENGTH_SHORT, true).show();

                    }else{
                        fauth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                String userid = fauth.getCurrentUser().getUid();
                                DocumentReference refer = fstore.collection("Customers").document(userid);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Email", email.getText().toString());
                                user.put("username", username.getText().toString());
                                user.put("Address", address.getText().toString());
                                user.put("Phone", phone.getText().toString());
                                user.put("Password", password.getText().toString());
                                refer.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toasty.success(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT, true).show();
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        finish();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.error(RegisterActivity.this, "Register unsuccessful \n " + e.getMessage(), Toast.LENGTH_SHORT, true).show();

                            }
                        });
                    }
                }
            });
        }
}