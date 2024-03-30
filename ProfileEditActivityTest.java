package com.example.wholesalesystem.helper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wholesalesystem.ProfileEditActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class ProfileEditActivityTest {

    private ProfileEditActivity profileEditActivity;

    @Before
    public void setUp() {
        profileEditActivity = Robolectric.buildActivity(ProfileEditActivity.class).create().visible().get();
    }

    @Test
    public void testPreconditions() {
        assertNotNull(profileEditActivity);
    }

    @Test
    public void testProfileUpdateSuccess() {
        Button registerButton = profileEditActivity.findViewById(R.id.registerevent);
        assertNotNull(registerButton);
        EditText usernameEditText = profileEditActivity.findViewById(R.id.registeruname);
        EditText passwordEditText = profileEditActivity.findViewById(R.id.registerpassword);
        EditText phoneEditText = profileEditActivity.findViewById(R.id.registerphone);
        EditText addressEditText = profileEditActivity.findViewById(R.id.registeraddress);
        EditText conpasswordEditText = profileEditActivity.findViewById(R.id.conregisterpassword);
        assertNotNull(usernameEditText);
        assertNotNull(passwordEditText);
        assertNotNull(phoneEditText);
        assertNotNull(addressEditText);
        assertNotNull(conpasswordEditText);

        // Set values in EditText fields
        usernameEditText.setText("testUsername");
        passwordEditText.setText("testPassword");
        phoneEditText.setText("testPhone");
        addressEditText.setText("testAddress");
        conpasswordEditText.setText("testPassword");

        // Mock FirebaseAuth and FirebaseFirestore
        FirebaseAuth mockedFirebaseAuth = mock(FirebaseAuth.class);
        FirebaseFirestore mockedFirestore = mock(FirebaseFirestore.class);
        profileEditActivity.fauth = mockedFirebaseAuth;
        profileEditActivity.fstore = mockedFirestore;

        // Mock getCurrentUser() method of FirebaseAuth
        FirebaseAuth.getInstance().getCurrentUser();
        when(mockedFirebaseAuth.getCurrentUser()).thenReturn(mock(FirebaseAuth.class));

        // Mock updatePassword() method of FirebaseAuth
        AuthCredential mockedAuthCredential = mock(AuthCredential.class);
        when(FirebaseAuth.getInstance().getCurrentUser().reauthenticate(mockedAuthCredential)).thenReturn(mock(Task.class));
        when(FirebaseAuth.getInstance().getCurrentUser().updatePassword(any())).thenReturn(mock(Task.class));

        // Mock update() method of FirebaseFirestore
        DocumentReference mockedDocumentReference = mock(DocumentReference.class);
        when(mockedFirestore.collection("Customers").document(any())).thenReturn(mockedDocumentReference);
        when(mockedDocumentReference.update(any())).thenReturn(mock(Task.class));

        // Perform button click
        registerButton.performClick();

        // Verify Toast message
        String toastMessage = ShadowToast.getTextOfLatestToast();
        assertEquals("Profile Update Successfull", toastMessage);

        // Verify Intent
        Intent expectedIntent = new Intent(profileEditActivity, LoginActivity.class);
        assertTrue(expectedIntent.filterEquals(ShadowApplication.getInstance().getNextStartedActivity()));

        // Verify updatePassword() method call
        verify(FirebaseAuth.getInstance().getCurrentUser()).updatePassword(any());
    }
}

