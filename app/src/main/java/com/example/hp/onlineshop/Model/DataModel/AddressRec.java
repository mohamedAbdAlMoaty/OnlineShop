package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 3/8/2019.
 */

public class AddressRec {
    private boolean status;
    private int code;
    @SerializedName("data")
    private DataRec dataRec;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataRec getDataRec() {
        return dataRec;
    }

    public void setDataRec(DataRec dataRec) {
        this.dataRec = dataRec;
    }
}
