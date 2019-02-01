package com.example.hp.onlineshop.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.onlineshop.Bases.BaseActivity;
import com.example.hp.onlineshop.Fragments.DetailsFragment;
import com.example.hp.onlineshop.Fragments.MapFragment;
import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.HotOfferDataItem;
import com.example.hp.onlineshop.Model.DataModel.UsedForSaleResponse;
import com.example.hp.onlineshop.Model.DataModel.UsedForSaleimage;
import com.example.hp.onlineshop.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsedForSalesActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,DetailsFragment.DetailsListener,MapFragment.MapListener {

    Toolbar toolbarUsedForSale;
    TextView title;
    String lang="en";
   private int id;
    private String price_before;
    private String price_after;
    private TabLayout tabs;
    private DetailsFragment detailsFragment;
    private MapFragment mapFragment;
    SliderLayout sliderLayout;
    ImageView like;
    TextView usedforsalenew,usedforsaleold;
    String detailsText;
    String lat;
    String lag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_for_sales);
        toolbarUsedForSale=findViewById(R.id.toolbarusedsales);
        tabs = findViewById(R.id.tabs);
        sliderLayout = findViewById(R.id.imageSlider);

        setSupportActionBar(toolbarUsedForSale);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbarUsedForSale.findViewById(R.id.toolbar_title_usedsales);
        title.setText(R.string.used);

        showProgressBar();

        tabs.addTab(tabs.newTab().setText(getString(R.string.map)));
        tabs.addTab(tabs.newTab().setText(getString(R.string.details)));
        tabs.addOnTabSelectedListener(this);
        detailsFragment = new DetailsFragment();
        detailsFragment.SetListner(this);
        mapFragment = new MapFragment();
        mapFragment.SetMapListner(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame,detailsFragment);
        ft.commit();





        HotOfferDataItem hotOfferDataItem = (HotOfferDataItem) getIntent().getParcelableExtra("HotOfferDataItem");
        id=hotOfferDataItem.getId();
        price_before=hotOfferDataItem.getPrice_before();
        price_after=hotOfferDataItem.getPrice_after();

        like=findViewById(R.id.usedforsalelike);
        usedforsalenew=findViewById(R.id.usedforsalenew);
        usedforsalenew.setText(price_after+" "+getString(R.string.kd));
        usedforsaleold=findViewById(R.id.usedforsaleold);
        usedforsaleold.setText(price_before+" "+getString(R.string.kd));

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

    private void getData() {
        String url="http://pazstore.com/api/";
        ApiRetrofit apiRetrofit= APIManager.getInstance(url);
        apiRetrofit.getUsedForSaleResponse(lang,id).enqueue(new Callback<UsedForSaleResponse>() {
            @Override
            public void onResponse(Call<UsedForSaleResponse> call, Response<UsedForSaleResponse> response) {
                hideProgressBar();

                ArrayList<UsedForSaleimage> imgarr= response.body().getData().getImages();
                sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
                sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :
                setSliderViews(imgarr);

                detailsText=response.body().getData().getNote();
                lat=response.body().getData().getLat();
                lag=response.body().getData().getLng();

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
    public void getDetails() {
            detailsFragment.edit(detailsText);
    }

    @Override
    public void getPosition() {
        mapFragment.setPosition(lat,lag);
    }
}
