package com.example.hp.onlineshop.UI.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.Activation;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.Utils.Constaints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidationActivity extends BaseActivity {

    EditText activationCode;
    TextView skip;
    Button login;
    String token;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);
        activationCode=findViewById(R.id.activationCode);
        skip=findViewById(R.id.Skipactive);
        login=findViewById(R.id.Loginactive);
        sharedPreferences = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE);
        token=getIntent().getStringExtra(Constaints.TOKEN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ValidationActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }


    public void getData() {

        String activation_code=activationCode.getText().toString();

        String urllanguage=Constaints.URL_LANGUAGE;
        ApiRetrofit apiRetrofit= APIManager.getInstance(urllanguage);
        showProgressBar();
        apiRetrofit.getactivation("Bearer "+token,Constaints.LANG,activation_code).enqueue(new Callback<Activation>() {
            @Override
            public void onResponse(Call<Activation> call, Response<Activation> response) {
                hideProgressBar();
                if(response.body().getCode() == 200) {


                // save token in sharedPreferences to be able to transfer around the project
                editor = sharedPreferences.edit();
                editor.putString(Constaints.TOKEN, token);
                editor.apply();



                    Intent intent = new Intent(ValidationActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Activation> call, Throwable t) {
                hideProgressBar();
            }
        });
    }
}
