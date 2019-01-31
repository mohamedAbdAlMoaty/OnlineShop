package com.example.hp.onlineshop.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.onlineshop.Bases.BaseActivity;
import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.Response;
import com.example.hp.onlineshop.R;

import retrofit2.Call;
import retrofit2.Callback;


public class LoginActivity extends BaseActivity {

    EditText phoneNumber,firstName,lastName;
    Button login;
    TextView skip;
    String token;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber=findViewById(R.id.PhoneNumber);
        firstName=findViewById(R.id.FirstName);
        lastName=findViewById(R.id.LastName);
        login=findViewById(R.id.login);
        skip=findViewById(R.id.Skip);
       sharedPreferences = getSharedPreferences("appSharedPre", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });


    }

    private void setData() {
        String phone=phoneNumber.getText().toString();
        String name=firstName.getText().toString()+lastName.getText().toString();

        showProgressBar();
        String urllanguage="http://services-apps.net/paz/api/";
        ApiRetrofit apiRetrofit= APIManager.getInstance(urllanguage);
        apiRetrofit.getResponse(phone,"test",name).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
               hideProgressBar();
                token= response.body().getData().getToken();

               // save token in sharedPreferences to be able to transfer around the project
                editor = sharedPreferences.edit();
                editor.putString("token", token);
                editor.apply();

                Intent intent=new Intent(LoginActivity.this,ValidationActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
