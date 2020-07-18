package com.example.hp.onlineshop.UI.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;

import java.util.concurrent.Future;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN,"");
                        if(token!=null && token.length()>0){
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        }
                        else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }
                        finish();
                    }
                }, 3000);

    }
}
