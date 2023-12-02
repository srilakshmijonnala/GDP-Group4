package com.example.wholesalesystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wholesalesystem.admin.AdminMainActivity;
import com.example.wholesalesystem.admin.EditProductActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class custproduct_list_adapter extends RecyclerView.Adapter<custproduct_list_adapter.ViewHolder> implements Filterable {
    private List<Product> pductArrayList;
    private List<Product> exampleListFull;
    private Context context;

    public custproduct_list_adapter(List<Product> pductArrayList, Context context) {
        this.pductArrayList = pductArrayList;
        exampleListFull = new ArrayList<>(pductArrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public custproduct_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull custproduct_list_adapter.ViewHolder holder, int position) {

        Product pduct = pductArrayList.get(position);
        holder.category.setText(Category.getCategoryname()[pduct.getCategoryid()]);
        holder.name.setText(pduct.getName());
        //holder.date.setText(pduct.getDate());
        holder.quantity.setText("Q: "+ pduct.getQuantity());
        holder.price.setText("Price : "+pduct.getSaleprice());

        if(pduct.getDescription().length()>140){
            holder.desc.setText("Brand Name : "+ pduct.getBrand() + "\nDescription : "+pduct.getDescription().substring(0, 140)+"....");

        }else{
            holder.desc.setText("Brand Name : "+ pduct.getBrand() + "\nDescription : "+pduct.getDescription());
        }

        holder.img.setBackgroundResource(Category.getCategoryicon()[pduct.getCategoryid()]);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return pductArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView category, name, desc, price, date, quantity;
        private final CardView relativeLayout;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            category = itemView.findViewById(R.id.category);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);
            quantity = itemView.findViewById(R.id.quantity);
            relativeLayout = itemView.findViewById(R.id.layout);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                int filterPattern = Integer.parseInt(constraint.toString());
                for (Product item : exampleListFull) {
                    if (item.getCategoryid()==filterPattern) {
                        filteredList.add(item);
                    }else if(100==filterPattern){
                        filteredList.add(item);
                    }
                }
                if(999==filterPattern){
                    filteredList.sort((obj1, obj2) -> {
                        return String.valueOf(obj1.getName()).compareToIgnoreCase(String.valueOf(obj1.getName()));
                    });
                }else if(1000==filterPattern){
                    filteredList.sort((obj1, obj2) -> {
                        return String.valueOf(obj2.getSaleprice()).compareToIgnoreCase(String.valueOf(obj1.getSaleprice()));
                    });
                }
            }
            Log.e("SDS", filteredList.toString());
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            pductArrayList.clear();
            pductArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



    public void sortNameByDes() {
        Comparator<Product> comparator = new Comparator<Product>() {

            @Override
            public int compare(Product object1, Product object2) {
                return Double.compare(object2.getSaleprice(),object1.getSaleprice());
            }
        };
        Collections.sort(pductArrayList, comparator);
        notifyDataSetChanged();
    }

    public void shuffle(){
        Collections.shuffle(pductArrayList);
        notifyDataSetChanged();
    }
    public void sortNameByAsc() {
        Comparator<Product> comparator = new Comparator<Product>() {

            @Override
            public int compare(Product object1, Product object2) {
                return Double.compare(object1.getSaleprice(),object2.getSaleprice());
            }
        };
        Collections.sort(pductArrayList, comparator);
        notifyDataSetChanged();
    }
}
