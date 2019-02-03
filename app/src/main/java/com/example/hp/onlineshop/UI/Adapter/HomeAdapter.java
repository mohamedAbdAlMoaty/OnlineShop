package com.example.hp.onlineshop.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.DataModel.Product;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Activities.UsedForSalesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 1/26/2019.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private ArrayList<Product> grp;
    private Context context;
    public HomeAdapter(ArrayList<Product> grp, Context context) {
        this.grp= grp;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,null);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.item_name.setText(grp.get(position).getTitle());
        holder.price.setText(grp.get(position).getPriceAfter());
        holder.old_price.setText(grp.get(position).getPriceBefore());
        Picasso.with(context).load(grp.get(position).getImage()).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return grp .size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView like,item_image;
        TextView item_name,price,old_price;

        public ViewHolder(View itemView) {
            super(itemView);
            like=itemView.findViewById(R.id.like);
            item_image=itemView.findViewById(R.id.item_image);
            item_name=itemView.findViewById(R.id.item_name);
            price=itemView.findViewById(R.id.price);
            old_price=itemView.findViewById(R.id.old_price);
        }
    }
}
