package com.example.hp.onlineshop.Model.DataModel;

/**
 * Created by HP on 2/8/2019.
 */

public class BaseResponse {

    protected boolean status;
    protected int code;


    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public boolean getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

}
