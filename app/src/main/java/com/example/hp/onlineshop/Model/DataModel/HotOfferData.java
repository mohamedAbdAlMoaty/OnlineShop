package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HP on 1/30/2019.
 */

public class HotOfferData {

    private int count_total;
    private String nextPageUrl=null;
    private int pages;
    @SerializedName("data")
    private ArrayList<HotOfferDataItem> hotOfferDataItems;

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<HotOfferDataItem> getHotOfferDataItems() {
        return hotOfferDataItems;
    }

    public void setHotOfferDataItems(ArrayList<HotOfferDataItem> hotOfferDataItems) {
        this.hotOfferDataItems = hotOfferDataItems;
    }
}
