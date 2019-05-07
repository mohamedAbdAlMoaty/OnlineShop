package com.example.hp.onlineshop.Model.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HP on 3/1/2019.
 */

public class ProfileInfo implements Serializable {

    private boolean status;
    private int code;
    @SerializedName("data")
    private DataProfile dataProfile;


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

    public DataProfile getDataProfile() {
        return dataProfile;
    }

    public void setDataProfile(DataProfile dataProfile) {
        this.dataProfile = dataProfile;
    }

}
