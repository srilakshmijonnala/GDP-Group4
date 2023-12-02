package com.example.wholesalesystem.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wholesalesystem.Category;
import com.example.wholesalesystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class EditProductActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {


    ImageView img;
    Spinner spin;

    EditText name, purprice, saleprice, brand, des,quantity;
    TextView date;
    DatePickerDialog datePickerDialog;
    int catid = -1;
    FirebaseFirestore fstore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        spin = findViewById(R.id.category);
        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        fstore = FirebaseFirestore.getInstance();
        purprice = findViewById(R.id.purprice);
        saleprice = findViewById(R.id.saleprice);
        des = findViewById(R.id.productdetails);
        quantity = findViewById(R.id.quantity);
        brand = findViewById(R.id.brand);
        date = findViewById(R.id.date);
        spin.setOnItemSelectedListener(EditProductActivity.this);

        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                Category.getCategoryname());

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spin.setAdapter(ad);

        spin.setSelection(0);
        catid =1;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            spin.setSelection(bundle.getInt("1"));
            catid =bundle.getInt("1");
            name.setText(bundle.getString("2"));
            date.setText(bundle.getString("3"));
            quantity.setText(String.valueOf(bundle.getInt("4")));
            purprice.setText(String.valueOf(bundle.getDouble("5")));
            saleprice.setText(String.valueOf(bundle.getDouble("6")));
            brand.setText(bundle.getString("7"));
            des.setText(bundle.getString("8"));
            uid= bundle.getString("9");
        }


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().length()==0 && purprice.getText().length()==0 &&date.getText().length()==0 &&des.getText().length()==0 &&quantity.getText().length()==0 &&saleprice.getText().length()==0 && brand.getText().length()==0){
                    Toasty.warning(EditProductActivity.this, "All Fields Are Required!!", Toast.LENGTH_SHORT, true).show();
                }else if(catid==-1){
                    Toasty.warning(EditProductActivity.this, "Category Must be Selected!!", Toast.LENGTH_SHORT, true).show();
                }else{
                    Map<String, Object> items = new HashMap<>();
                    items.put("Name", name.getText().toString());
                    items.put("Category ID", catid);
                    items.put("Brand", brand.getText().toString());
                    items.put("Quantity", quantity.getText().toString());
                    items.put("Date", date.getText().toString());
                    items.put("Description", des.getText().toString());
                    items.put("Purchase Price", Double.parseDouble(purprice.getText().toString()));
                    items.put("Sale Price", Double.parseDouble(saleprice.getText().toString()));
                    fstore.collection("Products").document(uid).update(items).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toasty.success(EditProductActivity.this, "Product Updated Successfully", Toast.LENGTH_SHORT, true).show();
                            startActivity(new Intent(EditProductActivity.this, AdminMainActivity.class));
                            finish();
                        }
                    });
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditProductActivity.this,
                        (view1, year, monthOfYear, dayOfMonth) -> date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        catid = i;
        img.setImageResource(Category.getCategoryicon()[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        catid = -1;
    }
}