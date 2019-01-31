package com.example.hp.onlineshop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp.onlineshop.Adapter.DepartmentAdapter;
import com.example.hp.onlineshop.Adapter.HomeAdapter;
import com.example.hp.onlineshop.Bases.BaseFragment;
import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.HomeResponse;
import com.example.hp.onlineshop.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 1/24/2019.
 */

public class HomeFragment extends BaseFragment {

    RecyclerView recyclerHome;
    ImageView image1,image2,image3;
    String lang="en";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.homefragment, container, false);
        recyclerHome=rootView.findViewById(R.id.recyclerHome);
        image1=rootView.findViewById(R.id.image1);
        image2=rootView.findViewById(R.id.image2);
        image3=rootView.findViewById(R.id.image3);
        showProgressBar();
        getData();
        return rootView;
    }

    private void getData() {
        String url="http://pazstore.com/api/";
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.HomeResponse(lang).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                hideProgressBar();
                Picasso.with(getContext()).load(response.body().getHomeData().getUpBanner().get(0).getImage()).into(image1);
                Picasso.with(getContext()).load(response.body().getHomeData().getUpBanner().get(1).getImage()).into(image2);
                Picasso.with(getContext()).load(response.body().getHomeData().getDownBanner().getImage()).into(image3);
                recyclerHome.setHasFixedSize(true);
                recyclerHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
                recyclerHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                recyclerHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
                HomeAdapter adapter=new HomeAdapter(response.body().getHomeData().getHot(),getContext());
                recyclerHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

            }
        });
    }
}
