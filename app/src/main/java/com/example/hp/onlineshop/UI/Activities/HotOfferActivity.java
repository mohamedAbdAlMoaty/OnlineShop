package com.example.hp.onlineshop.UI.Activities;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.Model.DataModel.HotOfferResponse;
import com.example.hp.onlineshop.UI.Adapter.HotOfferAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotOfferActivity extends BaseActivity {

    Toolbar toolbarhotoffer;
    TextView title;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_offer);
        recyclerView = findViewById(R.id.recyclerHotOffer);
        toolbarhotoffer=findViewById(R.id.toolbarhotoffer);
        setSupportActionBar(toolbarhotoffer);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbarhotoffer.findViewById(R.id.toolbar_title_hotoffer);
        title.setText(R.string.hotoffer);

        showProgressBar();
        getData();
    }

    private void getData() {
        String url=Constaints.URL_HOT_OFFER;
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.getHotOfferResponse(Constaints.LANG).enqueue(new Callback<HotOfferResponse>() {
            @Override
            public void onResponse(Call<HotOfferResponse> call, Response<HotOfferResponse> response) {
                hideProgressBar();
                ArrayList<HotOfferDataItem> hotOfferDataArrayList = response.body().getData().getHotOfferDataItems();

                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(HotOfferActivity.this,DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(HotOfferActivity.this,DividerItemDecoration.VERTICAL));
                recyclerView.setLayoutManager(new GridLayoutManager(HotOfferActivity.this,2));
                HotOfferAdapter adapter=new HotOfferAdapter(hotOfferDataArrayList,HotOfferActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HotOfferResponse> call, Throwable t) {

            }
        });
    }
}
