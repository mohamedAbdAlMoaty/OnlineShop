package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 1/25/2019.
 */

public class CategoriesResponse {

    private int code;
    @SerializedName("data")
    private Categories data;
    private boolean status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Categories getData() {
        return data;
    }

    public void setData(Categories data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
