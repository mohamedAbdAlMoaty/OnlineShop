package com.example.hp.onlineshop.Model.DataModel;

/**
 * Created by HP on 1/30/2019.
 */

public class UsedForSaleResponse {

    private int code;
    private UsedForSaleData data;
    private boolean status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UsedForSaleData getData() {
        return data;
    }

    public void setData(UsedForSaleData data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
