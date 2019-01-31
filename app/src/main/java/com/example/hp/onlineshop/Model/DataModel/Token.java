package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;


public  class Token {
    /**
     * user_id : 1
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9zZXJ2aWNlcy1hcHBzLm5ldC9wYXovYXBpL3VzZXIvYXV0aCIsImlhdCI6MTUzMjQ1NTY3MywiZXhwIjoxNTMzNjY1MjczLCJuYmYiOjE1MzI0NTU2NzMsImp0aSI6IlJHRnphd0RuVUdLT0RBaloifQ.JiiV7QEspEK-VV1ibYiK-itCgglMeUlzZoLzHrxG2hI
     * mobile : 01140125924
     * first_name : ayman jk
     * last_name : khalata elkobra-monofia
     * address : 8
     * avatar : http://services-apps.net/paz/assets/tmp/0620c169b825cbdde0fb3c0f4da8d2e4-14331-1447884.png
     */

    private int user_id;
    @SerializedName("token")
    private String accessToken;
    private String mobile;
    private String first_name;
    private String last_name;
    private String address;
    private String avatar;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getMobile() {
        return mobile;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatar() {
        return avatar;
    }
}
