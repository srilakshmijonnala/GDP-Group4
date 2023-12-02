package com.example.wholesalesystem.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import com.example.wholesalesystem.Category;
import com.example.wholesalesystem.Product;
import com.example.wholesalesystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class product_list_adapter extends RecyclerView.Adapter<product_list_adapter.ViewHolder> implements Filterable {
    private List<Product> pductArrayList;
    private List<Product> exampleListFull;
    private Context context;

    public product_list_adapter(List<Product> pductArrayList, Context context) {
        this.pductArrayList = pductArrayList;
        exampleListFull = new ArrayList<>(pductArrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public product_list_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull product_list_adapter.ViewHolder holder, int position) {

        Product pduct = pductArrayList.get(position);
        holder.category.setText(Category.getCategoryname()[pduct.getCategoryid()]);
        holder.name.setText(pduct.getName());
        holder.date.setText(pduct.getDate());
        holder.quantity.setText("Q: "+ pduct.getQuantity());
        holder.price.setText("Puurchase Price : "+ pduct.getPurprice()+ "\nSale Price : "+pduct.getSaleprice());

        if(pduct.getDescription().length()>140){
            holder.desc.setText("Brand Name : "+ pduct.getBrand() + "\nDescription : "+pduct.getDescription().substring(0, 140)+"....");

        }else{
            holder.desc.setText("Brand Name : "+ pduct.getBrand() + "\nDescription : "+pduct.getDescription());
        }

        holder.img.setBackgroundResource(Category.getCategoryicon()[pduct.getCategoryid()]);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("You Edit Or Delete?")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(context, EditProductActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("1", pduct.getCategoryid());
                                bundle.putString("2", pduct.getName());
                                bundle.putString("3", pduct.getDate());
                                bundle.putInt("4", pduct.getQuantity());
                                bundle.putDouble("5", pduct.getPurprice());
                                bundle.putDouble("6", pduct.getSaleprice());
                                bundle.putString("7", pduct.getBrand());
                                bundle.putString("8", pduct.getDescription());
                                bundle.putString("9", pduct.getUid());
                                i.putExtras(bundle);
                                context.startActivity(i);
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Products").document(pduct.getUid())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toasty.success(context, "Product is Deleted!!", Toast.LENGTH_SHORT, true).show();
                                                context.startActivity(new Intent(context, AdminMainActivity.class));
                                                ((Activity)context).finish();
                                            }
                                        });
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

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
            }
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
}
