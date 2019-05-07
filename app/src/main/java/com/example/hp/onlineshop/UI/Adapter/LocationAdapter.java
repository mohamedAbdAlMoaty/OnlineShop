package com.example.hp.onlineshop.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.DataModel.DataOflocation;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Activities.Address_list_Activity;
import com.example.hp.onlineshop.UI.Activities.EditActivity;
import com.example.hp.onlineshop.UI.Activities.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 3/6/2019.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>  {

    private ArrayList<DataOflocation> grp;
    private Context context;
    LocationLisner locationLisner;

    private int row=-1;

    public LocationAdapter (ArrayList<DataOflocation> grp, LocationLisner locationLisner,Context context) {
        this.grp= grp;
        this.locationLisner=locationLisner;
        this.context=context;
    }


    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_adapter,null);
        return new LocationAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final LocationAdapter.ViewHolder holder, final int position) {
        holder.title.setText(grp.get(position).getTitle());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationLisner.itemClicked(grp.get(position));
                row=position;
                notifyDataSetChanged();
            }
        });

        if(row==position){
            holder.item.setBackgroundColor(context.getResources().getColor(R.color.blue));
        }else{
            holder.item.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,EditActivity.class);
                intent.putExtra("itemdeleted",grp.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return grp.size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView title,edit;
        View item;

        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.rec_title);
            edit=itemView.findViewById(R.id.rec_edit);
            item=itemView;
        }
    }

    public interface LocationLisner{
        void itemClicked(DataOflocation dataOflocation);
    }

}
