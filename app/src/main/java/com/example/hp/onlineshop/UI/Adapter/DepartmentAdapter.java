package com.example.hp.onlineshop.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.DataModel.Category;
import com.example.hp.onlineshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 1/25/2019.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    private ArrayList<Category> grp;
    private Context context;
    public DepartmentAdapter(ArrayList<Category> grp, Context context) {
        this.grp= grp;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_item,null);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(grp.get(position).getImage()).into(holder.imageView);
        holder.titles.setText(grp.get(position).getTitle().toString());
        holder.subTitles.setText(context.getString(R.string.items)+" "+String.valueOf(grp.get(position).getProducts()));
    }

    @Override
    public int getItemCount() {
        return grp .size();
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titles;
        TextView subTitles;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgdepartment);
            titles=itemView.findViewById(R.id.title_depart);
            subTitles=itemView.findViewById(R.id.subtitlesdepartment);
        }
    }
}
