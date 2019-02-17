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

import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Activities.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 2/15/2019.
 */

public class LikesAdapter  extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    private ArrayList<HotOfferDataItem> grp;
    private Context context;
    LikesAction likesAction;

    public LikesAdapter (ArrayList<HotOfferDataItem> grp, Context context,LikesAction likesAction) {
        this.grp= grp;
        this.context=context;
        this.likesAction=likesAction;
    }


    @Override
    public LikesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.like_item,null);
        return new LikesAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final LikesAdapter.ViewHolder holder, final int position) {
        holder.like_name.setText(grp.get(position).getTitle());
        holder.like_price.setText(grp.get(position).getPrice_before()+" "+context.getString(R.string.kd));
        holder.like_price.setText(grp.get(position).getPrice_after()+" "+context.getString(R.string.kd));
        Picasso.with(context).load(grp.get(position).getImage()).into(holder.like_image);
        if(grp.get(position).isFav()){
            holder.like.setImageResource(R.mipmap.ic_like);
        }
        else{
            holder.like.setImageResource(R.mipmap.ic_un_like);
        }

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likesAction.onClick(position,grp.get(position));
            }
        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ProductActivity.class);
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

        ImageView like,like_image;
        TextView like_name,like_price,like_old_price,like_time;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            like=itemView.findViewById(R.id.like_like);
            like_image=itemView.findViewById(R.id.like_image);
            like_name=itemView.findViewById(R.id.like_name);
            like_price=itemView.findViewById(R.id.like_price);
            like_old_price=itemView.findViewById(R.id.like_old_price);
            like_time=itemView.findViewById(R.id.like_time);
            linearLayout=itemView.findViewById(R.id.like_layoutall);
        }
    }


    public interface LikesAction{
        void onClick(int pos,HotOfferDataItem hotOfferDataItem);
    }


}
