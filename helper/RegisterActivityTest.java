package com.example.wholesalesystem.helper;


import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityTest {

    @Mock
    private FirebaseAuth mockedFirebaseAuth;

    @Mock
    private FirebaseFirestore mockedFirestore;

    @Mock
    private DocumentReference mockedDocumentReference;

    private RegisterActivity registerActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registerActivity = new RegisterActivity();
        registerActivity.fauth = mockedFirebaseAuth;
        registerActivity.fstore = mockedFirestore;
    }

    @Test
    public void testValidRegistration() {
        // Mock EditText fields
        EditText usernameEditText = new EditText(registerActivity);
        EditText passwordEditText = new EditText(registerActivity);
        EditText emailEditText = new EditText(registerActivity);
        EditText phoneEditText = new EditText(registerActivity);
        EditText addressEditText = new EditText(registerActivity);
        EditText conpasswordEditText = new EditText(registerActivity);

        usernameEditText.setText("testUsername");
        passwordEditText.setText("testPassword");
        emailEditText.setText("test@example.com");
        phoneEditText.setText("1234567890");
        addressEditText.setText("123 Test Street");
        conpasswordEditText.setText("testPassword");

        // Mock createUserWithEmailAndPassword() method of FirebaseAuth
        when(mockedFirebaseAuth.createUserWithEmailAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(new TaskMock<AuthResult>().complete());

        // Mock collection(), document(), and set() methods of FirebaseFirestore
        when(mockedFirestore.collection("Customers")).thenReturn(mockedDocumentReference);
        when(mockedDocumentReference.document(ArgumentMatchers.anyString())).thenReturn(mockedDocumentReference);
        when(mockedDocumentReference.set(ArgumentMatchers.any())).thenReturn(new TaskMock<Void>().complete());

        // Perform registration
        registerActivity.username = usernameEditText;
        registerActivity.password = passwordEditText;
        registerActivity.email = emailEditText;


