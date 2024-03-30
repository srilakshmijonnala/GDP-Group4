package com.example.wholesalesystem.helper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.wholesalesystem.CustomerProfileActivity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerProfileActivityTest {

    private CustomerProfileActivity customerProfileActivity;

    @BeforeEach
    void setUp() {
        customerProfileActivity = new CustomerProfileActivity();
    }

    @Test
    void testPreconditions() {
        assertNotNull(customerProfileActivity);
    }

    @Test
    void testLogout() {
        customerProfileActivity.logout();

        // Assert that the activity should be finished after logout
        assertEquals(true, customerProfileActivity.isFinishing());
    }

    // Add more test cases as needed for other methods and functionalities of CustomerProfileActivity
}

