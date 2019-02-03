package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 1/26/2019.
 */

public class HomeResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private com.example.hp.onlineshop.Model.DataModel.HomeData HomeData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public com.example.hp.onlineshop.Model.DataModel.HomeData getHomeData() {
        return HomeData;
    }

    public void setHomeData(com.example.hp.onlineshop.Model.DataModel.HomeData homeData) {
        HomeData = homeData;
    }
}
