package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wholesalesystem.R;
import com.example.wholesalesystem.helper.Category;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AddProductActivityTest {

    private ActivityScenario<AddProductActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(AddProductActivity.class);
    }

    @Test
    public void testAddProductActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testCategorySpinnerInitialized() {
        activityScenario.onActivity(activity -> {
            Spinner spinner = activity.findViewById(R.id.category);
            assertNotNull(spinner);
            assertEquals(Category.getCategoryname().length, spinner.getCount());
        });
    }

    @Test
    public void testCategoryIdSelected() {
        activityScenario.onActivity(activity -> {
            Spinner spinner = activity.findViewById(R.id.category);
            assertNotNull(spinner);
            activity.runOnUiThread(() -> spinner.setSelection(1));
            assertEquals(1, activity.catid);
        });
    }

    @Test
    public void testCategoryIconSet() {
        activityScenario.onActivity(activity -> {
            Spinner spinner = activity.findViewById(R.id.category);
            assertNotNull(spinner);
            activity.runOnUiThread(() -> spinner.setSelection(2));
            int expectedDrawableId = Category.getCategoryicon()[2];
            int actualDrawableId = activity.img.getDrawable().getConstantState().hashCode();
            assertEquals(expectedDrawableId, actualDrawableId);
        });
    }

    @Test
    public void testAddProduct() {
        activityScenario.onActivity(activity -> {
            EditText nameEditText = activity.findViewById(R.id.name);
            EditText purPriceEditText = activity.findViewById(R.id.purprice);
            EditText salePriceEditText = activity.findViewById(R.id.saleprice);
            EditText brandEditText = activity.findViewById(R.id.brand);
            EditText desEditText = activity.findViewById(R.id.productdetails);
            EditText quantityEditText = activity.findViewById(R.id.quantity);
            TextView dateTextView = activity.findViewById(R.id.date);

            assertNotNull(nameEditText);
            assertNotNull(purPriceEditText);
            assertNotNull(salePriceEditText);
            assertNotNull(brandEditText);
            assertNotNull(desEditText);
            assertNotNull(quantityEditText);
            assertNotNull(dateTextView);

            FirebaseFirestore mockFirestore = FirebaseFirestore.getInstance();
            assertNotNull(mockFirestore);

            activity.runOnUiThread(() -> {
                nameEditText.setText("Test Product");
                purPriceEditText.setText("10.00");
                salePriceEditText.setText("20.00");
                brandEditText.setText("Test Brand");
                desEditText.setText("Test Description");
                quantityEditText.setText("5");
                dateTextView.setText("2024-04-05");
                activity.findViewById(R.id.add).performClick();
            });

            // Replace with assertions based on the success of adding a product to Firestore
        });
    }
}

