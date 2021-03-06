package com.example.hp.onlineshop.UI.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.Model.DataModel.HotOfferResponse;
import com.example.hp.onlineshop.UI.Adapter.HotOfferAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Fragments.HomeFragment;
import com.example.hp.onlineshop.Utils.Constaints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotOfferActivity extends BaseActivity implements HotOfferAdapter.LikeActionHotOffer {

    Toolbar toolbarhotoffer;
    TextView title;
    RecyclerView recyclerView;
    ApiRetrofit apiRetrofit;
    ArrayList<HotOfferDataItem> hotOfferDataArrayList;
    HotOfferAdapter adapter;

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

        String url=Constaints.URL_HOT_OFFER;
        apiRetrofit= APIManager.getInstance(url);

        showProgressBar();
        getData();
    }

    private void getData() {

        String token = "Bearer "+getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                getString(Constaints.TOKEN,"");
        Log.d("token",token);

        apiRetrofit.getHotOfferResponse(token,Constaints.LANG).enqueue(new Callback<HotOfferResponse>() {
            @Override
            public void onResponse(Call<HotOfferResponse> call, Response<HotOfferResponse> response) {
                hideProgressBar();
                 hotOfferDataArrayList = response.body().getData().getHotOfferDataItems();

                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(HotOfferActivity.this,DividerItemDecoration.HORIZONTAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(HotOfferActivity.this,DividerItemDecoration.VERTICAL));
                recyclerView.setLayoutManager(new GridLayoutManager(HotOfferActivity.this,2));
                adapter=new HotOfferAdapter(hotOfferDataArrayList,HotOfferActivity.this,HotOfferActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HotOfferResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(final HotOfferDataItem hotOfferDataItem) {
        String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                getString(Constaints.TOKEN,"");
        if(token == null||token.isEmpty()){
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(HotOfferActivity.this, LoginActivity.class));
                            finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }
        else{
            if(hotOfferDataItem.isFav()==false){
                showProgressBar();
                apiRetrofit.addToFav("Bearer "+token,Constaints.LANG,hotOfferDataItem.getId()).
                        enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                hideProgressBar();
                                if(response.code() ==200){
                                    hotOfferDataItem.setFav(true);   // change state now
                                    adapter.notifyDataSetChanged();

                                    /*
                                    or
                                    HotOfferAdapter adapter=new HotOfferAdapter(hotOfferDataArrayList,HotOfferActivity.this,HotOfferActivity.this);
                                    recyclerView.setAdapter(adapter);
                                    */

                                }
                                else{
                                    // show error message
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                hideProgressBar();
                                // show error message
                            }
                        });
            }
            else{

                showProgressBar();
                apiRetrofit.deleteFromFav("Bearer "+token,Constaints.LANG,hotOfferDataItem.getId()).
                        enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                hideProgressBar();
                                if(response.code() ==200){
                                    hotOfferDataItem.setFav(false);  // change state now
                                    adapter.notifyDataSetChanged();

                                    /*
                                    or
                                    HotOfferAdapter adapter=new HotOfferAdapter(hotOfferDataArrayList,HotOfferActivity.this,HotOfferActivity.this);
                                    recyclerView.setAdapter(adapter);
                                    */

                                }
                                else{
                                    // show error message
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse> call, Throwable t) {
                                hideProgressBar();
                                // show error message
                            }
                        });
            }
        }

    }
}
