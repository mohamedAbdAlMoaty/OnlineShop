package com.example.hp.onlineshop.UI.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.AddAddressInfo;
import com.example.hp.onlineshop.Model.DataModel.BaseResponse;
import com.example.hp.onlineshop.Model.DataModel.DataOflocation;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.Utils.Constaints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends BaseActivity {

    Toolbar toolbar;
    public TextView title,my_location,deleleted;
    String lng,lat;
    Button save,cancel;
    EditText name,address,governate,city,street,block,flat,floor;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar=findViewById(R.id.toolbar_edit_pro);
        deleleted=findViewById(R.id.deleted);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancle);
        name=findViewById(R.id.name);
        flat=findViewById(R.id.flat);
        floor=findViewById(R.id.floor);
        address=findViewById(R.id.address);
        governate=findViewById(R.id.governate);
        city=findViewById(R.id.city);
        street=findViewById(R.id.street);
        block=findViewById(R.id.block);
        my_location=findViewById(R.id.my_location);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbar.findViewById(R.id.toolbar_title_edit_edit);
        title.setText("Edit");

        check();

        my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditActivity.this,ProfileLocationActivity.class);
                startActivityForResult(intent,100);

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN, "");
                String url = Constaints.URL_USED_FOR_SALES;
                ApiRetrofit apiRetrofit = APIManager.getInstance(url);
                showProgressBar();
                apiRetrofit.deleteAddress("Bearer " + token, Constaints.LANG,id).enqueue(new Callback<AddAddressInfo>() {
                    @Override
                    public void onResponse(Call<AddAddressInfo> call, Response<AddAddressInfo> response) {
                        hideProgressBar();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AddAddressInfo> call, Throwable t) {
                        hideProgressBar();
                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN, "");
                if (token == null || token.isEmpty()) {
                    showConfirmationDialog("error", "please login first", "ok", "cancel"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(EditActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                }
                else if(lat==null || lng ==null||lat.trim().length()==0||lng.trim().length()==0){
                    showConfirmationDialog("error", "please fill up fields", "ok", "cancel"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(EditActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                }
                else {
                    String url = Constaints.URL_USED_FOR_SALES;
                    ApiRetrofit apiRetrofit = APIManager.getInstance(url);
                    showProgressBar();
                    apiRetrofit.addAddress("Bearer " + token, Constaints.LANG, Double.parseDouble(lat), Double.parseDouble(lng), address.getText().toString()
                            , name.getText().toString(), street.getText().toString(), block.getText().toString(), city.getText().toString(), governate.getText().toString(),
                            flat.getText().toString(), floor.getText().toString()).enqueue(new Callback<AddAddressInfo>() {
                        @Override
                        public void onResponse(Call<AddAddressInfo> call, Response<AddAddressInfo> response) {
                            hideProgressBar();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<AddAddressInfo> call, Throwable t) {
                            hideProgressBar();
                        }
                    });
                }

            }
        });

    }

    private void check() {
        DataOflocation dataOflocation=(DataOflocation)getIntent().getSerializableExtra("itemdeleted");

        if(dataOflocation !=null){
            deleleted.setVisibility(View.VISIBLE);
            id=dataOflocation.getId();
            name.setText(dataOflocation.getTitle());
            my_location.setText(dataOflocation.getLat()+" , "+dataOflocation.getLng());
            address.setText(dataOflocation.getAddress());
            street.setText(dataOflocation.getStreet());
            block.setText(dataOflocation.getBlock());
            city.setText(dataOflocation.getCity());
            governate.setText(dataOflocation.getGovernate());
            floor.setText(dataOflocation.getFloor());
            flat.setText(dataOflocation.getFlat());
        }else{
            deleleted.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100 && resultCode==RESULT_OK){
            lng=data.getStringExtra("long");
            lat=data.getStringExtra("lat");
            my_location.setText(lng +" , "+lat);
        }
    }
}
