package com.example.wholesalesystem.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wholesalesystem.Product;
import com.example.wholesalesystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Product> coursesrrayList;
    FirebaseFirestore fs;
    private product_list_adapter rvadapter;

    ImageView all, i1,i2,i3,i4,i5,i6,i7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        fs = FirebaseFirestore.getInstance();
        rv = findViewById(R.id.rv);
        all = findViewById(R.id.all);
        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);
        i4 = findViewById(R.id.i4);
        i5 = findViewById(R.id.i5);
        i6 = findViewById(R.id.i6);
        i7 = findViewById(R.id.i7);
        coursesrrayList = new ArrayList<>();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rvadapter = new product_list_adapter(coursesrrayList, this);
        ArrayList<Product> list = new ArrayList<>();
        fs.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot d : task.getResult()) {
                        list.add(new Product(String.valueOf(d.get("Name")), String.valueOf(d.get("Brand")), d.getId(), String.valueOf(d.get("Description")), String.valueOf(d.get("Date")),Double.parseDouble(d.get("Purchase Price").toString()),Double.parseDouble(d.get("Sale Price").toString()) , Integer.parseInt(d.get("Quantity").toString()), Integer.parseInt(d.get("Category ID").toString())));
                        rvadapter= new product_list_adapter(list, ProductActivity.this);
                        rv.setAdapter(rvadapter);
                    }
                    rvadapter.notifyDataSetChanged();
                }
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductActivity.this, AddProductActivity.class));
            }
        });
        all.setBackgroundColor(Color.parseColor("#95CEFFF4"));

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("100");
                all.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("0");
                i1.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("1");
                i2.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("2");
                i3.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("3");
                i4.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("4");
                i5.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("5");
                i6.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
                i7.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvadapter.getFilter().filter("6");
                i7.setBackgroundColor(Color.parseColor("#95CEFFF4"));
                i1.setBackgroundColor(Color.parseColor("#ffffff"));
                i2.setBackgroundColor(Color.parseColor("#ffffff"));
                i3.setBackgroundColor(Color.parseColor("#ffffff"));
                i4.setBackgroundColor(Color.parseColor("#ffffff"));
                i5.setBackgroundColor(Color.parseColor("#ffffff"));
                i6.setBackgroundColor(Color.parseColor("#ffffff"));
                all.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
    }
}