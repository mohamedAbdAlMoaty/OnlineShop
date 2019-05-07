package com.example.hp.onlineshop.Model.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HP on 3/1/2019.
 */

public class DataProfile implements Serializable {
    private int user_id;
    private String mobile;
    private String first_name;
    private String last_name;
    @SerializedName("address")
    private AddressProfile addressProfile;
    private String avatar;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public AddressProfile getAddressProfile() {
        return addressProfile;
    }

    public void setAddressProfile(AddressProfile addressProfile) {
        this.addressProfile = addressProfile;
    }
}
