package com.example.hp.onlineshop.Model.API;
import com.example.hp.onlineshop.Model.DataModel.Activation;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.CategoriesResponse;
import com.example.hp.onlineshop.Model.DataModel.HomeResponse;
import com.example.hp.onlineshop.Model.DataModel.HotOfferResponse;
import com.example.hp.onlineshop.Model.DataModel.Response;
import com.example.hp.onlineshop.Model.DataModel.UsedForSaleResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiRetrofit {

    @FormUrlEncoded
    @POST("user/register")
    Call<Response> getResponse(@Field("mobile_number") String mobile_number,
                               @Field("device_token") String device_token,
                               @Field("name") String name);

    @FormUrlEncoded
    @POST("user/activateAccount")
    Call<Activation>getactivation(@Header("Authorization") String Token, @Header("Lang") String Lang, @Field("activation_code") String activation_code);



    @POST("products/categories")
    Call<CategoriesResponse>getData(@Header("lang") String language);

    @POST("home")
    Call<HomeResponse>HomeResponse(@Header("Authorization") String Token,@Header("Lang") String Lang);

    @POST("products/deals/hot")
    Call<HotOfferResponse>getHotOfferResponse(@Header("Authorization") String Token,@Header("Lang") String Lang);

    @FormUrlEncoded
    @POST("products/details")
    Call<UsedForSaleResponse>getUsedForSaleResponse(@Header("Authorization") String Token,@Header("Lang") String Lang, @Field("id") int id);

    @FormUrlEncoded
    @POST("products/addFav")
    Call<BaseResponse>addToFav(@Header("Authorization") String Token,@Header("lang") String Lang,@Field("id")int id);

    @FormUrlEncoded
    @POST("products/deleteFav")
    Call<BaseResponse>deleteFromFav(@Header("Authorization") String Token,@Header("lang") String Lang,@Field("id")int id);

    @FormUrlEncoded
    @POST("orders/cart/add")
    Call<BaseResponse>addToCart(@Header("Authorization") String Token,
                                @Field("item_id")int id,@Field("number")int number);


    @POST("products/getFav")
    Call<HotOfferResponse>getFav(@Header("Authorization") String Token,@Header("lang") String Lang);

}
