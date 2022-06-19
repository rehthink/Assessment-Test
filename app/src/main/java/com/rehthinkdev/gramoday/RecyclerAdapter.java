package com.rehthinkdev.gramoday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rehthinkdev.gramoday.Model.Product;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Product[] products;

    public RecyclerAdapter(Product[] products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products[position];

        holder.itemsName.setText(products[position].getCmdtyStdName());
        holder.location.setText("Yeshwanthapura Mandi, Bangalore, KA");
        holder.price.setText("20-25");

    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemsName, location, price, time;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            itemsName = itemView.findViewById(R.id.itemsName);
            location = itemView.findViewById(R.id.location);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
