package com.example.hp.onlineshop.Model.API;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 1/21/2019.
 */

public class APIManager {


    // singleton
    private APIManager(){}


    private static Retrofit getRetrofit(String url){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.readTimeout(60,TimeUnit.SECONDS);
        okHttpClient.addInterceptor(logging);

        return  new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRetrofit getInstance(String url){
        return  getRetrofit(url).create(ApiRetrofit.class);
    }
}
