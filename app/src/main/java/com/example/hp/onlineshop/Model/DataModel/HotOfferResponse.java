package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 1/30/2019.
 */

public class HotOfferResponse {

    private int code;
    private HotOfferData data;
    private boolean status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HotOfferData getData() {
        return data;
    }

    public void setData(HotOfferData data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
