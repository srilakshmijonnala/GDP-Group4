package com.example.wholesalesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ProfileEditActivity extends AppCompatActivity {


    EditText username, phone, address, password, conpassword;

    FirebaseAuth fauth;

    FirebaseFirestore fstore;
    String uid="", emaiil="", oldpass ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);



        username = findViewById(R.id.registeruname);
        password = findViewById(R.id.registerpassword);
        phone = findViewById(R.id.registerphone);
        address = findViewById(R.id.registeraddress);
        conpassword = findViewById(R.id.conregisterpassword);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        CardView register = findViewById(R.id.registerevent);


        FirebaseFirestore.getInstance().collection("Customers").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(d -> {
            if(d.exists()){
                uid = d.getId();
                username.setText(d.getString("username"));
                phone.setText(d.getString("Phone"));
               emaiil = d.getString("Email");
                address.setText(d.getString("Address"));
                password.setText(d.getString("Password"));
                oldpass = d.getString("Password");
                conpassword.setText(d.getString("Password"));
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().length()==0 && password.getText().length()==0 && phone.getText().length()==0 && address.getText().length()==0 && conpassword.getText().length()==0){
                    Toasty.warning(ProfileEditActivity.this, "All Fields Are Required!!", Toast.LENGTH_SHORT, true).show();
                }else if((password.getText().toString().length()<=5)){
                    Toasty.warning(ProfileEditActivity.this, "Password Must be 6 Character.", Toast.LENGTH_SHORT, true).show();
                }else if(!(password.getText().toString().equals(conpassword.getText().toString()))){
                    Toasty.warning(ProfileEditActivity.this, "Password & Confirm Password Must be Same. (" +password.getText().toString()+") & ("+conpassword.getText().toString()+")", Toast.LENGTH_SHORT, true).show();

                }else {

                    if(oldpass.equals(password.getText().toString())){

                        DocumentReference refer = fstore.collection("Customers").document(uid);
                        Map<String, Object> user = new HashMap<>();
                        user.put("username", username.getText().toString());
                        user.put("Address", address.getText().toString());
                        user.put("Phone", phone.getText().toString());
                        user.put("Password", password.getText().toString());
                        refer.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toasty.success(ProfileEditActivity.this, "Profile Update Successfull", Toast.LENGTH_SHORT, true).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        });

                    }else{

                        AuthCredential credential = EmailAuthProvider.getCredential(emaiil,oldpass);

                        FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    FirebaseAuth.getInstance().getCurrentUser().updatePassword(password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(!task.isSuccessful()){
                                                Toasty.error(ProfileEditActivity.this, "Something went wrong. Please try again later", Toast.LENGTH_SHORT, true).show();
                                            }else {

                                                DocumentReference refer = fstore.collection("Customers").document(uid);
                                                Map<String, Object> user = new HashMap<>();
                                                user.put("username", username.getText().toString());
                                                user.put("Address", address.getText().toString());
                                                user.put("Phone", phone.getText().toString());
                                                user.put("Password", password.getText().toString());
                                                refer.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toasty.success(ProfileEditActivity.this, "Profile Update Successfull", Toast.LENGTH_SHORT, true).show();
                                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                        finish();
                                                    }
                                                });

                                            }
                                        }
                                    });
                                }else { Toasty.error(ProfileEditActivity.this, "Authentication Failed", Toast.LENGTH_SHORT, true).show();

                                }
                            }

                        });
                        }
                    }
                }
        });
        }

}