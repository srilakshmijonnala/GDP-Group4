package com.example.wholesalesystem.helper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

package com.example.wholesalesystem.helper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wholesalesystem.CartActivity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CartActivityTest {

    private CartActivity cartActivity;

    @BeforeEach
    void setUp() {
        cartActivity = new CartActivity();
    }

    @Test
    void setTotal() {
        ArrayList<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(1, "1", 10.0, "Test Product", 2.0, 20.0, 1, "1"));
        cartItemModelList.add(new CartItemModel(2, "2", 15.0, "Another Product", 3.0, 45.0, 1, "2"));

        cartActivity.cartItemModelList = cartItemModelList;

        cartActivity.settotal();

        assertEquals(95.0, cartActivity.totalvalue);
        assertEquals("$95.00", cartActivity.totalcartamount.getText());
    }

    // Add more test cases as needed for other methods and functionalities of CartActivity
}

