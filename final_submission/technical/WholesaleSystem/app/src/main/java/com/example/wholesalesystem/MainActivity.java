package com.example.wholesalesystem;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wholesalesystem.admin.AddProductActivity;
import com.example.wholesalesystem.admin.AdminMainActivity;
import com.example.wholesalesystem.admin.ProductActivity;
import com.example.wholesalesystem.admin.product_list_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    ImageView menu, profile;
    private RecyclerView rv;
    private ArrayList<Product> arrlist;
    Spinner spin;
    FirebaseFirestore fs;
    private custproduct_list_adapter rvadapter;

    ImageView all, i1,i2,i3,i4,i5,i6,i7;

    TextView empty_view;
    String[] filter = {
            "Featured",
            "Price : Low to High",
            "Price : High to Low",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
        }
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        menu = findViewById(R.id.menu);
        spin = findViewById(R.id.filter);
        empty_view = findViewById(R.id.empty_view);
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



        spin.setOnItemSelectedListener(MainActivity.this);

        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                filter);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spin.setAdapter(ad);

        spin.setSelection(0);


        arrlist = new ArrayList<>();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rvadapter = new custproduct_list_adapter(arrlist, this);

        fs.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot d : task.getResult()) {
                        arrlist.add(new Product(String.valueOf(d.get("Name")), String.valueOf(d.get("Brand")), d.getId(), String.valueOf(d.get("Description")), String.valueOf(d.get("Date")),Double.parseDouble(d.get("Purchase Price").toString()),Double.parseDouble(d.get("Sale Price").toString()) , Integer.parseInt(d.get("Quantity").toString()), Integer.parseInt(d.get("Category ID").toString())));
                        rvadapter= new custproduct_list_adapter(arrlist, MainActivity.this);
                        rv.setAdapter(rvadapter);
                    }
                    if(arrlist.isEmpty()){
                        Log.e(String.valueOf(arrlist.isEmpty()), arrlist.toString());
                        empty_view.setVisibility(View.VISIBLE);
                    }
                    rvadapter.notifyDataSetChanged();
                }
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

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CustomerProfileActivity.class);
            startActivity(intent);
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==1){
            rvadapter.sortNameByDes();
        }else if(i==2){
            rvadapter.sortNameByAsc();
        }else{
            rvadapter.shuffle();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}