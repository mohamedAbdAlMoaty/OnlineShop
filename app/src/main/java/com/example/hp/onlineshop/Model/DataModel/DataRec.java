package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HP on 3/8/2019.
 */

public class DataRec {
    private int count_total;
    private String nextPageUrl;
    private int pages;
    @SerializedName("data")
    private ArrayList<DataOflocation> dataOflocations;

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

    public ArrayList<DataOflocation> getDataOflocations() {
        return dataOflocations;
    }

    public void setDataOflocations(ArrayList<DataOflocation> dataOflocations) {
        this.dataOflocations = dataOflocations;
    }
}
