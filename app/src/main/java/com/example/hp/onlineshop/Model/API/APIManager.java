package com.example.hp.onlineshop.Model.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 1/21/2019.
 */

public class APIManager {


    // singleton
    private APIManager(){}


    private static Retrofit getRetrofit(String url){

        return  new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRetrofit getInstance(String url){
        return  getRetrofit(url).create(ApiRetrofit.class);
    }
}
