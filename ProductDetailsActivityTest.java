package com.example.wholesalesystem.helper;

package com.example.wholesalesystem;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wholesalesystem.helper.CartItemModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class ProductDetailsActivityTest {

    private ProductDetailsActivity productDetailsActivity;

    @Before
    public void setUp() {
        productDetailsActivity = Robolectric.buildActivity(ProductDetailsActivity.class).create().visible().get();
    }

    @Test
    public void testPreconditions() {
        assertNotNull(productDetailsActivity);
    }

    @Test
    public void testAddToCartButton() {
        Button addToCartButton = productDetailsActivity.findViewById(R.id.add_to_cart_btn);
        assertNotNull(addToCartButton);
        addToCartButton.performClick();
        String toastMessage = ShadowToast.getTextOfLatestToast();
        assertEquals("Item Is Added To The Cart!!", toastMessage);
    }

    @Test
    public void testBuyNowButton() {
        Button buyNowButton = productDetailsActivity.findViewById(R.id.buy_now_btn);
        assertNotNull(buyNowButton);
        buyNowButton.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alertDialog);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        Intent expectedIntent = new Intent(productDetailsActivity, CheckoutActivity.class);
        assertTrue(expectedIntent.filterEquals(ShadowApplication.getInstance().getNextStartedActivity()));
    }

    @Test
    public void testCartItemModelCreation() {
        productDetailsActivity.addtocart.performClick();

        // Mock FirebaseFirestore.getInstance() method
        FirebaseFirestore mockedFirestore = mock(FirebaseFirestore.class);
        when(mockedFirestore.collection("Cart")).thenReturn(mockedFirestore);
        when(mockedFirestore.add(any())).thenReturn(mock(DocumentReference.class));

        // Mock QuerySnapshot
        QuerySnapshot mockedQuerySnapshot = mock(QuerySnapshot.class);
        when(mockedQuerySnapshot.size()).thenReturn(0);

        // Mock Task
        Task mockedTask = mock(Task.class);
        when(mockedTask.isSuccessful()).thenReturn(true);
        when(mockedTask.getResult()).thenReturn(mockedQuerySnapshot);

        // Mock FirebaseFirestore.getInstance() method
        FirebaseFirestore mockedFirestore = mock(FirebaseFirestore.class);
        when(mockedFirestore.collection("Cart")).thenReturn(mockedFirestore);
        when(mockedFirestore.whereEqualTo("User ID", "testUserId")).thenReturn(mockedFirestore);
        when(mockedFirestore.whereEqualTo("Product ID", "testProductId")).thenReturn(mockedFirestore);
        when(mockedFirestore.get()).thenReturn(mockedTask);

        // Verify
        verify(mockedFirestore).add(any());

        List<CartItemModel> cartItemModelList = productDetailsActivity.cartItemModelList;
        assertNotNull(cartItemModelList);
        assertEquals(1, cartItemModelList.size());
        CartItemModel cartItemModel = cartItemModelList.get(0);
        assertEquals("testCategoryId", cartItemModel.getCategoryId());
        assertEquals("testUserId", cartItemModel.getUserId());
        assertEquals("testProductId", cartItemModel.getProductId());
        assertEquals("testProductName", cartItemModel.getProductName());
        assertEquals(10, cartItemModel.getQuantity());
        assertEquals(9.99, cartItemModel.getSalePrice(), 0.01);
        assertEquals(19.99, cartItemModel.getPrice(), 0.01);
    }
}

