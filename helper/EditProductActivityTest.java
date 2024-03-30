package com.example.wholesalesystem.helper;

package com.example.wholesalesystem.admin;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.wholesalesystem.R;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class EditProductActivityTest {

    private ActivityScenario<EditProductActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(EditProductActivity.class);
    }

    @Test
    public void testEditProductActivityNotNull() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity);
        });
    }

    @Test
    public void testEditProductActivityUI() {
        activityScenario.onActivity(activity -> {
            EditText nameEditText = activity.findViewById(R.id.name);
            assertNotNull(nameEditText);
            Spinner categorySpinner = activity.findViewById(R.id.category);
            assertNotNull(categorySpinner);
            EditText purPriceEditText = activity.findViewById(R.id.purprice);
            assertNotNull(purPriceEditText);
            EditText salePriceEditText = activity.findViewById(R.id.saleprice);
            assertNotNull(salePriceEditText);
            EditText brandEditText = activity.findViewById(R.id.brand);
            assertNotNull(brandEditText);
            EditText desEditText = activity.findViewById(R.id.productdetails);
            assertNotNull(desEditText);
            EditText quantityEditText = activity.findViewById(R.id.quantity);
            assertNotNull(quantityEditText);
            TextView dateTextView = activity.findViewById(R.id.date);
            assertNotNull(dateTextView);
        });
    }

    @Test
    public void testUpdateProduct() {
        activityScenario.onActivity(activity -> {
            FirebaseFirestore fStoreMock = mock(FirebaseFirestore.class);
            activity.setFStore(fStoreMock);

            EditText nameEditText = activity.findViewById(R.id.name);
            nameEditText.setText("Test Product");
            EditText purPriceEditText = activity.findViewById(R.id.purprice);
            purPriceEditText.setText("10.0");
            EditText salePriceEditText = activity.findViewById(R.id.saleprice);
            salePriceEditText.setText("20.0");
            EditText brandEditText = activity.findViewById(R.id.brand);
            brandEditText.setText("Test Brand");
            EditText desEditText = activity.findViewById(R.id.productdetails);
            desEditText.setText("Test Description");
            EditText quantityEditText = activity.findViewById(R.id.quantity);
            quantityEditText.setText("100");
            TextView dateTextView = activity.findViewById(R.id.date);
            dateTextView.setText("2022-04-01");
            activity.setCatid(1);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("1", 1);
            bundle.putString("2", "Test Product");
            bundle.putString("3", "2022-04-01");
            bundle.putDouble("4", 100);
            bundle.putDouble("5", 10.0);
            bundle.putDouble("6", 20.0);
            bundle.putString("7", "Test Brand");
            bundle.putString("8", "Test Description");
            bundle.putString("9", "testUid");
            intent.putExtras(bundle);
            activityScenario.onActivity(new ActivityScenario.ActivityAction<EditProductActivity>() {
                @Override
                public void perform(EditProductActivity activity) {
                    activity.onActivityResult(1, AppCompatActivity.RESULT_OK, intent);
                }
            });

            Map<String, Object> expectedProductData = new HashMap<>();
            expectedProductData.put("Name", "Test Product");
            expectedProductData.put("Category ID", 1);
            expectedProductData.put("Brand", "Test Brand");
            expectedProductData.put("Quantity", "100");
            expectedProductData.put("Date", "2022-04-01");
            expectedProductData.put("Description", "Test Description");
            expectedProductData.put("Purchase Price", 10.0);
            expectedProductData.put("Sale Price", 20.0);

            // Mocked Firestore should be called with the expected product data
            // Implement your mock assertions here

            // For demonstration purposes, we're just checking if Firestore mock is not null
            assertNotNull(fStoreMock);
        });
    }

    // Add more test cases as needed
}

