package com.example.hp.onlineshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.onlineshop.Activities.UsedForSalesActivity;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 1/29/2019.
 */

public class HotOfferAdapter extends RecyclerView.Adapter<HotOfferAdapter.ViewHolder> {

    private ArrayList<HotOfferDataItem> grp;
    private Context context;

    public HotOfferAdapter(ArrayList<HotOfferDataItem> grp, Context context) {
        this.grp= grp;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_offer_item,null);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.hotoffer_name.setText(grp.get(position).getTitle());
        holder.hotofferold_price.setText(grp.get(position).getPrice_before()+" "+context.getString(R.string.kd));
        holder.hotofferprice.setText(grp.get(position).getPrice_after()+" "+context.getString(R.string.kd));
        Picasso.with(context).load(grp.get(position).getImage()).into(holder.hotoffer_image);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UsedForSalesActivity.class);
                intent.putExtra("HotOfferDataItem",grp.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return grp.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView like,hotoffer_image;
        TextView hotoffer_name,hotofferprice,hotofferold_price,hotoffertime;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            like=itemView.findViewById(R.id.hotofferlike);
            hotoffer_image=itemView.findViewById(R.id.hotoffer_image);
            hotoffer_name=itemView.findViewById(R.id.hotoffer_name);
            hotofferprice=itemView.findViewById(R.id.hotofferprice);
            hotofferold_price=itemView.findViewById(R.id.hotofferold_price);
            hotoffertime=itemView.findViewById(R.id.hotoffertime);
            linearLayout=itemView.findViewById(R.id.layoutall);
        }
    }

}


