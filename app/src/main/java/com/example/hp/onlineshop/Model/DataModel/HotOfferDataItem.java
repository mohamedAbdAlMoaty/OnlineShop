package com.example.hp.onlineshop.Model.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 1/30/2019.
 */

public class HotOfferDataItem implements Parcelable {

    private int id;
    private String title;
    private String end_date;
    private String price_before;
    private String price_after;
    private int quntaty;
    private String image;

    protected HotOfferDataItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        end_date = in.readString();
        price_before = in.readString();
        price_after = in.readString();
        quntaty = in.readInt();
        image = in.readString();
    }

    public static final Creator<HotOfferDataItem> CREATOR = new Creator<HotOfferDataItem>() {
        @Override
        public HotOfferDataItem createFromParcel(Parcel in) {
            return new HotOfferDataItem(in);
        }

        @Override
        public HotOfferDataItem[] newArray(int size) {
            return new HotOfferDataItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public int getQuntaty() {
        return quntaty;
    }

    public void setQuntaty(int quntaty) {
        this.quntaty = quntaty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(end_date);
        parcel.writeString(price_before);
        parcel.writeString(price_after);
        parcel.writeInt(quntaty);
        parcel.writeString(image);
    }
}
