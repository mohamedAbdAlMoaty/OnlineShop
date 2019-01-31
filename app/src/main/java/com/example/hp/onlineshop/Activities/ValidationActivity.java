package com.example.hp.onlineshop.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.onlineshop.Bases.BaseActivity;
import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.Activation;
import com.example.hp.onlineshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidationActivity extends BaseActivity {

    EditText activationCode;
    TextView skip;
    Button login;
    SharedPreferences sharedPreferences;
    String token;
    String lang="en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);
        activationCode=findViewById(R.id.activationCode);
        skip=findViewById(R.id.Skipactive);
        login=findViewById(R.id.Loginactive);
        sharedPreferences = getSharedPreferences("appSharedPre", MODE_PRIVATE);
        token=sharedPreferences.getString("token","");

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

        String urllanguage="http://services-apps.net/paz/api/";
        ApiRetrofit apiRetrofit= APIManager.getInstance(urllanguage);
        apiRetrofit.getactivation(token,lang,activation_code).enqueue(new Callback<Activation>() {
            @Override
            public void onResponse(Call<Activation> call, Response<Activation> response) {
                Intent intent=new Intent(ValidationActivity.this,HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Activation> call, Throwable t) {

            }
        });
    }
}
