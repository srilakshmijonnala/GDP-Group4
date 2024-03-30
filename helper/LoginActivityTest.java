package com.example.wholesalesystem.helper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.widget.EditText;

import com.example.wholesalesystem.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginActivityTest {

    private LoginActivity loginActivity;

    @BeforeEach
    void setUp() {
        loginActivity = new LoginActivity();
    }

    @Test
    void testPreconditions() {
        assertNotNull(loginActivity);
    }

    @Test
    void testIsValidCredentials() {
        // Test valid credentials
        assertEquals(true, loginActivity.isValidCredentials("admin", "admin"));

        // Test invalid credentials
        assertEquals(false, loginActivity.isValidCredentials("user", "pass"));
    }

    @Test
    void testSignInWithEmailAndPassword() {
        // Mock FirebaseAuth instance
        FirebaseAuth mockedFirebaseAuth = mock(FirebaseAuth.class);
        loginActivity.fauth = mockedFirebaseAuth;

        // Mock EditText fields
        EditText mockedEmailEditText = mock(EditText.class);
        EditText mockedPasswordEditText = mock(EditText.class);
        when(mockedEmailEditText.getText()).thenReturn(mock(CharSequence.class));
        when(mockedPasswordEditText.getText()).thenReturn(mock(CharSequence.class));
        when(mockedEmailEditText.length()).thenReturn(1);
        when(mockedPasswordEditText.length()).thenReturn(1);

        // Mock Task<AuthResult> object
        Task<AuthResult> mockedTask = mock(Task.class);
        when(mockedTask.isSuccessful()).thenReturn(true);

        // Mock signInWithEmailAndPassword method
        when(mockedFirebaseAuth.signInWithEmailAndPassword("admin", "admin")).thenReturn(mockedTask);

        // Mock OnCompleteListener for signInWithEmailAndPassword
        OnCompleteListener<AuthResult> mockedCompleteListener = mock(OnCompleteListener.class);

        // Call the method
        loginActivity.signInWithEmailAndPassword("admin", "admin", mockedCompleteListener);

        // Add assertions based on the behavior you expect
    }

    // Add more test cases as needed for other methods and functionalities of LoginActivity
}
