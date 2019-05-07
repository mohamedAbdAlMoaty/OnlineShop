package com.example.hp.onlineshop.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.AddressRec;
import com.example.hp.onlineshop.Model.DataModel.CategoriesResponse;
import com.example.hp.onlineshop.Model.DataModel.Category;
import com.example.hp.onlineshop.Model.DataModel.DataOflocation;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Adapter.DepartmentAdapter;
import com.example.hp.onlineshop.UI.Adapter.LocationAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.Utils.Constaints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Address_list_Activity extends BaseActivity implements LocationAdapter.LocationLisner {

    RecyclerView recyclerView;
    Button add_location,choose_location;
    String titleLocation;
    int idLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list_);
        recyclerView=findViewById(R.id.recycle_location);
        add_location=findViewById(R.id.add_location);
        choose_location=findViewById(R.id.choose_location);
        showProgressBar();

        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Address_list_Activity.this,EditActivity.class);
                startActivity(intent);

            }
        });

        choose_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(titleLocation !=null){
                    Intent intent=new Intent();
                    intent.putExtra("titlelocation",titleLocation);
                    intent.putExtra("idlocation",idLocation);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private void getData() {
        String token = "Bearer "+getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                getString(Constaints.TOKEN,"");

        String url= Constaints.URL_HOME_DEPART_FRAGMENT;
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.getAddress("Bearer "+token,Constaints.LANG).enqueue(new Callback<AddressRec>() {
            @Override
            public void onResponse(Call<AddressRec> call, Response<AddressRec> response) {

                hideProgressBar();
                ArrayList<DataOflocation> dataOflocations = response.body().getDataRec().getDataOflocations();

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager( new LinearLayoutManager(Address_list_Activity.this));
                LocationAdapter locationAdapter=new LocationAdapter(dataOflocations,Address_list_Activity.this,Address_list_Activity.this);
                recyclerView.setAdapter(locationAdapter);
            }

            @Override
            public void onFailure(Call<AddressRec> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // call it from OnResume to be able to update it automaticaly when add new location
        getData();
    }

    @Override
    public void itemClicked(DataOflocation dataOflocation) {
        this.titleLocation=dataOflocation.getTitle();
        this.idLocation=dataOflocation.getId();
    }
}

