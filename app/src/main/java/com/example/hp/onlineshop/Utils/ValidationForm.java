package com.example.hp.onlineshop.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by HP on 2/1/2019.
 */

public class  ValidationForm {

    Context context;

    public ValidationForm(Context context){
        this.context=context;
    }

    public Boolean validName(String name){
        if(name.trim().isEmpty()){

            Toast.makeText(context, "empty field ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(name.trim().length() <2)
        {
            Toast.makeText(context, "low words ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean vaildPhone(String phone){
        if(phone.trim().isEmpty()){

            Toast.makeText(context, "empty field ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(phone.trim().length() !=11)
        {
            Toast.makeText(context, "number error ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
}
