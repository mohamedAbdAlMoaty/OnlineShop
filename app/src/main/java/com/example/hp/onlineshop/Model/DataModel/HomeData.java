package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class HomeData {
    @SerializedName("up_banner")
    @Expose
    private List<Banner> upBanner ;
    @SerializedName("down_banner")
    @Expose
    private Banner downBanner;
    @SerializedName("hot")
    @Expose
    private ArrayList<Product> hot ;
    @SerializedName("flash")
    @Expose
    private ArrayList<Product> flash = null;
    @SerializedName("newest")
    @Expose
    private ArrayList<Product> newest = null;


    public List<Banner> getUpBanner() {
        return upBanner;
    }

    public void setUpBanner(List<Banner> upBanner) {
        this.upBanner = upBanner;
    }

    public Banner getDownBanner() {
        return downBanner;
    }

    public void setDownBanner(Banner downBanner) {
        this.downBanner = downBanner;
    }

    public ArrayList<Product> getHot() {
        return hot;
    }

    public void setHot(ArrayList<Product> hot) {
        this.hot = hot;
    }

    public ArrayList<Product> getFlash() {
        return flash;
    }

    public void setFlash(ArrayList<Product> flash) {
        this.flash = flash;
    }

    public ArrayList<Product> getNewest() {
        return newest;
    }

    public void setNewest(ArrayList<Product> newest) {
        this.newest = newest;
    }
}
