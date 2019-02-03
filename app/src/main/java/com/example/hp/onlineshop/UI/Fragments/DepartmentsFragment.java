package com.example.hp.onlineshop.UI.Fragments;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.CategoriesResponse;
import com.example.hp.onlineshop.Model.DataModel.Category;
import com.example.hp.onlineshop.UI.Adapter.DepartmentAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 1/24/2019.
 */

public class DepartmentsFragment extends BaseFragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.departmentfragment, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_department);
        showProgressBar();
        getData();
        return rootView;
    }

    private void getData() {
        String url=Constaints.URL_HOME_DEPART_FRAGMENT;
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.getData(Constaints.LANG).enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                hideProgressBar();
              ArrayList<Category> categoryArrayList = response.body().getData().getData();

              recyclerView.setHasFixedSize(true);
              recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
              recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                DepartmentAdapter adapter=new DepartmentAdapter(categoryArrayList,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

                hideProgressBar();
                Log.e("DepartmentsFragment",t.getLocalizedMessage());
            }
        });
    }
}
