package com.example.hp.onlineshop.UI.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.HomeResponse;
import com.example.hp.onlineshop.Model.DataModel.Product;
import com.example.hp.onlineshop.UI.Activities.HotOfferActivity;
import com.example.hp.onlineshop.UI.Activities.LoginActivity;
import com.example.hp.onlineshop.UI.Adapter.HomeAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



import static android.content.Context.MODE_PRIVATE;



public class HomeFragment extends BaseFragment implements HomeAdapter.LikeAction {

    RecyclerView recyclerHome;
    ImageView image1,image2,image3;
    TextView seeAll;
    SharedPreferences sharedPreferences;
    String token;
    ArrayList<Product> hotProductArrayList;
    ApiRetrofit apiRetrofit;
    HomeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.homefragment, container, false);
        recyclerHome=rootView.findViewById(R.id.recyclerHome);
        image1=rootView.findViewById(R.id.image1);
        image2=rootView.findViewById(R.id.image2);
        image3=rootView.findViewById(R.id.image3);
        seeAll=rootView.findViewById(R.id.home_see_all);

        String url=Constaints.URL_HOME_DEPART_FRAGMENT;
        apiRetrofit= APIManager.getInstance(url);

        sharedPreferences = getContext().getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE);

        showProgressBar();
        getData();

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), HotOfferActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

    private void getData() {


        token="Bearer "+sharedPreferences.getString(Constaints.TOKEN,"");
        apiRetrofit.HomeResponse(token,Constaints.LANG).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                hideProgressBar();
                hotProductArrayList = response.body().getHomeData().getHot();
                Picasso.with(getContext()).load(response.body().getHomeData().getUpBanner().get(0).getImage()).into(image1);
                Picasso.with(getContext()).load(response.body().getHomeData().getUpBanner().get(1).getImage()).into(image2);
                Picasso.with(getContext()).load(response.body().getHomeData().getDownBanner().getImage()).into(image3);
                recyclerHome.setHasFixedSize(true);
                recyclerHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
                recyclerHome.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                recyclerHome.setLayoutManager(new GridLayoutManager(getActivity(),2));
                adapter=new HomeAdapter(hotProductArrayList,getContext(),HomeFragment.this);
                recyclerHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(final Product product) {
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
            if(product.isFav()==false){
                showProgressBar();
                apiRetrofit.addToFav("Bearer "+token,Constaints.LANG,product.getId()).
                        enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                hideProgressBar();
                                if(response.code() ==200){
                                    product.setFav(true);   // change state now
                                    adapter.notifyDataSetChanged();

                                    /*
                                    or
                                    HomeAdapter adapter=new HomeAdapter(hotProductArrayList,getContext(),HomeFragment.this);
                                    recyclerHome.setAdapter(adapter);
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
                apiRetrofit.deleteFromFav("Bearer "+token,Constaints.LANG,product.getId()).
                        enqueue(new Callback<BaseResponse>() {
                            @Override
                            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                hideProgressBar();
                                if(response.code() ==200){
                                    product.setFav(false);  // change state now
                                    adapter.notifyDataSetChanged();

                                    /*
                                    or
                                    HomeAdapter adapter=new HomeAdapter(hotProductArrayList,getContext(),HomeFragment.this);
                                    recyclerHome.setAdapter(adapter);
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
