package com.example.hp.onlineshop.UI.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.Model.DataModel.UsedForSaleResponse;
import com.example.hp.onlineshop.Model.DataModel.UsedForSaleimage;
import com.example.hp.onlineshop.UI.Adapter.HotOfferAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.UI.Fragments.DetailsFragment;
import com.example.hp.onlineshop.UI.Fragments.MapFragment;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,DetailsFragment.DetailsListener,MapFragment.MapListener {

    Toolbar toolbarUsedForSale;
    TextView title;
   private int id;
   private int number=1;
    private TabLayout tabs;
    private DetailsFragment detailsFragment;
    private MapFragment mapFragment;
    SliderLayout sliderLayout;
    ImageView like;
    TextView usedforsalenew,usedforsaleold;
    String detailsText;
    String lat;
    String lag;
    boolean isfavourite;
    TextView addCard;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_for_sales);
        toolbarUsedForSale=findViewById(R.id.toolbarusedsales);
        like=findViewById(R.id.usedforsalelike);
        usedforsalenew=findViewById(R.id.usedforsalenew);
        usedforsaleold=findViewById(R.id.usedforsaleold);
        addCard=findViewById(R.id.addtocard);
        tabs = findViewById(R.id.tabs);
        sliderLayout = findViewById(R.id.imageSlider);
        //or  send data to fragment using interface
        detailsFragment = new DetailsFragment();        // we cant make constractor so will make method
        detailsFragment.SetListner(this);   //according to interface
        mapFragment = new MapFragment();                 // we cant make constractor so will make method
        mapFragment.SetMapListner(this);      //according to interface


        /*
        //or send data to fragment using bundle
        bundle=new Bundle();
        detailsFragment = new DetailsFragment();
        mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);
        detailsFragment.setArguments(bundle);
        */


        setSupportActionBar(toolbarUsedForSale);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbarUsedForSale.findViewById(R.id.toolbar_title_usedsales);
        title.setText(R.string.used);

        showProgressBar();

        final HotOfferDataItem hotOfferDataItem = (HotOfferDataItem) getIntent().getParcelableExtra("HotOfferDataItem");
        id=hotOfferDataItem.getId();

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                        getString(Constaints.TOKEN,"");
                if(token == null||token.isEmpty()){
                    showConfirmationDialog("error", "please login first", "ok", "cancel"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ProductActivity.this, LoginActivity.class));
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
                    if(isfavourite==false){
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
                                            like.setImageResource(R.mipmap.ic_like);
                                            isfavourite=true;
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
                                            like.setImageResource(R.mipmap.ic_un_like);
                                            isfavourite=false;

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
        });


        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicesAddCard();
            }
        });








        getData();
    }

    private void setSliderViews(final ArrayList<UsedForSaleimage> imgarr ) {
        String imgUrl;
        for (int i = 0; i < imgarr.size(); i++) {

            SliderView sliderView = new DefaultSliderView(this);
            sliderView.setImageUrl(imgarr.get(i).getImage());
            imgUrl=imgarr.get(i).getImage();
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final String finalImgUrl = imgUrl;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(finalImgUrl));
                    startActivity(i);
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    private void servicesAddCard(){

        String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN,"");
        if(token == null||token.isEmpty()){
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(ProductActivity.this, LoginActivity.class));
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
            String url=Constaints.URL_USED_FOR_SALES;
            ApiRetrofit apiRetrofit= APIManager.getInstance(url);
            showProgressBar();
            apiRetrofit.addToCart("Bearer "+token,id,number).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    hideProgressBar();
                    if(response.code()==200){
                        // how success message
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    hideProgressBar();
                    // show
                }
            });
        }


    }
    private void getData() {
        String url=Constaints.URL_USED_FOR_SALES;
        String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN,"");
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.getUsedForSaleResponse("Bearer "+token,Constaints.LANG,id).enqueue(new Callback<UsedForSaleResponse>() {
            @Override
            public void onResponse(Call<UsedForSaleResponse> call, Response<UsedForSaleResponse> response) {
                hideProgressBar();

                detailsText=response.body().getData().getNote();
                //using bundle
               // bundle.putString("details",detailsText);

                lat=response.body().getData().getLat();
                lag=response.body().getData().getLng();
                /*
                //using bundle
                bundle.putString("lat",lat);
                bundle.putString("lang",lag);
               */
                ArrayList<UsedForSaleimage> imgarr= response.body().getData().getImages();

                sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
                sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :
                setSliderViews(imgarr);
                isfavourite=response.body().getData().isFav();
                if(isfavourite){
                    like.setImageResource(R.mipmap.ic_like);
                }
                else{
                    like.setImageResource(R.mipmap.ic_un_like);
                }


                usedforsalenew.setText(response.body().getData().getPrice_after()+" "+getString(R.string.kd));
                usedforsaleold.setText(response.body().getData().getPrice_before()+" "+getString(R.string.kd));


                tabs.addTab(tabs.newTab().setText(getString(R.string.map)));
                tabs.addTab(tabs.newTab().setText(getString(R.string.details)));
                tabs.addOnTabSelectedListener(ProductActivity.this);


                //default opened fragment
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame,mapFragment);
                ft.commit();

            }

            @Override
            public void onFailure(Call<UsedForSaleResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (tab.getPosition()){
            case 0:
                ft.replace(R.id.main_frame,mapFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,detailsFragment);
                ft.commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public String getDetails() {
        return detailsText;
    }



    @Override
    public String lat() {
        return lat;
    }

    @Override
    public String lang() {
        return lag;
    }
}
