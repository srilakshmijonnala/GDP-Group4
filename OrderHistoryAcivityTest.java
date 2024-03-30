package com.example.wholesalesystem.helper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.widget.TextView;

import com.example.wholesalesystem.helper.OrderModel;
import com.example.wholesalesystem.helper.custorder_list_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OrderHistoryActivityTest {

    private OrderHistoryActivity orderHistoryActivity;

    @BeforeEach
    void setUp() {
        orderHistoryActivity = new OrderHistoryActivity();
    }

    @Test
    void testPreconditions() {
        assertNotNull(orderHistoryActivity);
    }

    @Test
    void testOnBackPressed() {
        // Mock startActivity method
        OrderHistoryActivity mockedOrderHistoryActivity = mock(OrderHistoryActivity.class);
        when(mockedOrderHistoryActivity.startActivity(mockedOrderHistoryActivity.getIntent())).thenReturn(null);

        // Call the onBackPressed method
        orderHistoryActivity.onBackPressed();

        // Add assertions based on the behavior you expect
    }

    @Test
    void testOrderModelCreation() {
        // Create a sample QueryDocumentSnapshot
        QueryDocumentSnapshot mockedQueryDocumentSnapshot = mock(QueryDocumentSnapshot.class);
        when(mockedQueryDocumentSnapshot.get("User")).thenReturn("testUser");
        when(mockedQueryDocumentSnapshot.get("Name")).thenReturn("testName");
        when(mockedQueryDocumentSnapshot.get("Address")).thenReturn("testAddress");
        when(mockedQueryDocumentSnapshot.get("Pick Up Slot")).thenReturn("testPickupSlot");
        when(mockedQueryDocumentSnapshot.get("Paid by")).thenReturn("testPaymentMethod");
        when(mockedQueryDocumentSnapshot.get("Price")).thenReturn("testPrice");
        when(mockedQueryDocumentSnapshot.getId()).thenReturn("testOrderId");
        when(mockedQueryDocumentSnapshot.getBoolean("Delivery")).thenReturn(true);
        when(mockedQueryDocumentSnapshot.getTimestamp("Date")).thenReturn(Timestamp.now());
        when(mockedQueryDocumentSnapshot.get("Sts")).thenReturn(1);

        // Create a sample group of items
        List<Map<String, Object>> group = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("Product Name", "Test Product");
        item.put("Product ID", "123");
        item.put("Category ID", "456");
        item.put("Quantity", 2);
        item.put("Price", 10.99);
        item.put("Sale Price", 9.99);
        group.add(item);

        // Mock the QuerySnapshot
        QuerySnapshot mockedQuerySnapshot = mock(QuerySnapshot.class);
        when(mockedQuerySnapshot.isSuccessful()).thenReturn(true);
        when(mockedQuerySnapshot.getResult()).thenReturn(mockedQueryDocumentSnapshot);

        // Mock the Task
        Task<QuerySnapshot> mockedTask = mock(Task.class);
        when(mockedTask.isSuccessful()).thenReturn(true);
        when(mockedTask.getResult()).thenReturn(mockedQuerySnapshot);

        // Mock FirebaseFirestore.getInstance() method
        FirebaseFirestore mockedFirestore = mock(FirebaseFirestore.class);
        when(mockedFirestore.collection("Order")).thenReturn(mockedFirestore);
        when(mockedFirestore.whereEqualTo("User", "testUser")).thenReturn(mockedFirestore);
        when(mockedFirestore.orderBy("Date", Query.Direction.DESCENDING)).thenReturn(mockedFirestore);
        when(mockedFirestore.get()).thenReturn(mockedTask);

        // Call the onCreate method
        orderHistoryActivity.onCreate(null);

        // Verify that the OrderModel was created properly
        assertNotNull(orderHistoryActivity.arrlist);
        assertEquals(1, orderHistoryActivity.arrlist.size());
        OrderModel orderModel = orderHistoryActivity.arrlist.get(0);
        assertEquals("testUser", orderModel.getUser());
        assertEquals("testName", orderModel.getName());
        assertEquals("testAddress", orderModel.getAddress());
        assertEquals("testPickupSlot", orderModel.getPickUpSlot());
        assertEquals("testPaymentMethod", orderModel.getPaidBy());
        assertEquals("testPrice", orderModel.getPrice());
        assertEquals("testOrderId", orderModel.getOrderId());
        assertEquals(true, orderModel.isDelivery());
        assertNotNull(orderModel.getDate());
        assertEquals(1, orderModel.getSts());
        assertNotNull(orderModel.getGroup());
        assertEquals(1, orderModel.getGroup().size());
        assertEquals("Test Product", orderModel.getGroup().get(0).get("Product Name"));
        assertEquals("123", orderModel.getGroup().get(0).get("Product ID"));
        assertEquals("456", orderModel.getGroup().get(0).get("Category ID"));
        assertEquals(2, orderModel.getGroup().get(0).get("Quantity"));
        assertEquals(10.99, orderModel.getGroup().get(0).get("Price"));
        assertEquals(9.99, orderModel.getGroup().get(0).get("Sale Price"));
    }

    // Add more test cases as needed for other methods and functionalities of OrderHistoryActivity
}
