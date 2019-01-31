package com.example.hp.onlineshop.Model.DataModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 1/22/2019.
 */

public class Activation {

    /**
     * status : true
     * code : 200
     * data : {"user_id":1,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9zZXJ2aWNlcy1hcHBzLm5ldC9wYXovYXBpL3VzZXIvYXV0aCIsImlhdCI6MTUzMjQ1NTY3MywiZXhwIjoxNTMzNjY1MjczLCJuYmYiOjE1MzI0NTU2NzMsImp0aSI6IlJHRnphd0RuVUdLT0RBaloifQ.JiiV7QEspEK-VV1ibYiK-itCgglMeUlzZoLzHrxG2hI","mobile":"01140125924","first_name":"ayman jk","last_name":"khalata elkobra-monofia","address":"8","avatar":"http://services-apps.net/paz/assets/tmp/0620c169b825cbdde0fb3c0f4da8d2e4-14331-1447884.png"}
     */

    private boolean status;
    private int code;
    @SerializedName("data")
    private Token token;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public Token getToken() {
        return token;
    }
}
