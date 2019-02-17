package com.example.hp.onlineshop.UI.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.HomeResponse;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.Model.DataModel.HotOfferResponse;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Activities.HotOfferActivity;
import com.example.hp.onlineshop.UI.Activities.LoginActivity;
import com.example.hp.onlineshop.UI.Activities.ProductActivity;
import com.example.hp.onlineshop.UI.Adapter.HomeAdapter;
import com.example.hp.onlineshop.UI.Adapter.HotOfferAdapter;
import com.example.hp.onlineshop.UI.Adapter.LikesAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;
import com.example.hp.onlineshop.Utils.Constaints;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HP on 2/10/2019.
 */

public class LikesFragment  extends BaseFragment implements LikesAdapter.LikesAction {

    RecyclerView recyclerView;
    ArrayList<HotOfferDataItem> hotOfferDataArrayList;
    LikesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.like_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_likes);
        getData();
        return rootView;
    }

    private void getData() {


        String token = getContext().getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                getString(Constaints.TOKEN,"");
        if(token == null||token.isEmpty()){
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }
        else{
            String url=Constaints.URL_HOME_DEPART_FRAGMENT;
            ApiRetrofit apiRetrofit= APIManager.getInstance(url);
            showProgressBar();
            apiRetrofit.getFav("Bearer "+token,Constaints.LANG).enqueue(new Callback<HotOfferResponse>() {
                @Override
                public void onResponse(Call<HotOfferResponse> call, Response<HotOfferResponse> response) {
                    hideProgressBar();

                    hotOfferDataArrayList = response.body().getData().getHotOfferDataItems();

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter=new  LikesAdapter(hotOfferDataArrayList,getActivity(),LikesFragment.this);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<HotOfferResponse> call, Throwable t) {

                }
            });

        }


    }

    @Override
    public void onClick(final int pos, final HotOfferDataItem hotOfferDataItem) {

        String token = getContext().getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN,"");
        if(token == null||token.isEmpty()){
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
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
                String url=Constaints.URL_USED_FOR_SALES;
                ApiRetrofit apiRetrofit= APIManager.getInstance(url);
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
                                    LikesAdapter adapter=new LikesAdapter(hotOfferDataArrayList,getContext(),LikesFragment.this);
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
                String url=Constaints.URL_USED_FOR_SALES;
                ApiRetrofit apiRetrofit= APIManager.getInstance(url);
                apiRetrofit.deleteFromFav("Bearer "+token,Constaints.LANG,hotOfferDataItem.getId()).
                        enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                hideProgressBar();
                                if(response.code() ==200){
                                    hotOfferDataItem.setFav(false);  // change state now
                                    hotOfferDataArrayList.remove(pos);
                                    adapter.notifyDataSetChanged();

                                    /*
                                    or
                                    LikesAdapter adapter=new LikesAdapter(hotOfferDataArrayList,getContext(),LikesFragment.this);
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
