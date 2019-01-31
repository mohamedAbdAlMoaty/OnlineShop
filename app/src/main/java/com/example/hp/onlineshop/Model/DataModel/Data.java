package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 1/25/2019.
 */

public class Data {
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("token")
        @Expose
        private String token;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

}
