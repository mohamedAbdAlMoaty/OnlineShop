package com.example.hp.onlineshop.Model.DataModel;

import java.util.ArrayList;

/**
 * Created by HP on 1/30/2019.
 */

public class UsedForSaleData {

    private int id;
    private String title;
    private String image;
    private String note;
    private String price_before;
    private String price_after;
    private String category;
    private String end_date;
    private int  quntaty;
    private String lat;
    private String lng;
    private ArrayList<UsedForSaleimage> images;
    private boolean fav;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice_before() {
        return price_before;
    }

    public void setPrice_before(String price_before) {
        this.price_before = price_before;
    }

    public String getPrice_after() {
        return price_after;
    }

    public void setPrice_after(String price_after) {
        this.price_after = price_after;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getQuntaty() {
        return quntaty;
    }

    public void setQuntaty(int quntaty) {
        this.quntaty = quntaty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public ArrayList<UsedForSaleimage> getImages() {
        return images;
    }

    public void setImages(ArrayList<UsedForSaleimage> images) {
        this.images = images;
    }
}
