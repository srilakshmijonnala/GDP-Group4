package com.example.wholesalesystem.helper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.wholesalesystem.CheckoutActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutActivityTest {

    private CheckoutActivity checkoutActivity;

    @BeforeEach
    void setUp() {
        checkoutActivity = new CheckoutActivity();
    }

    @Test
    void testPreconditions() {
        assertNotNull(checkoutActivity);
    }

    @Test
    void testSendData() {
        // Simulating sending data with all required fields
        checkoutActivity.name.setText("John Doe");
        checkoutActivity.address.setText("123 Main Street");

        checkoutActivity.sendData("Cash");

        // Assert the visibility of ProgressBar
        assertEquals(View.VISIBLE, checkoutActivity.pbar.getVisibility());
    }

    // Add more test cases as needed for other methods and functionalities of CheckoutActivity
}
