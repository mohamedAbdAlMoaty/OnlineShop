package com.example.hp.onlineshop.UI.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.AddAddressInfo;
import com.example.hp.onlineshop.Model.DataModel.ProfileInfo;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.example.hp.onlineshop.Utils.Constaints;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingProfileActivity extends BaseActivity {

    TextView address;
    EditText firstName,lastName,phone;
    ProfileInfo profileInfo;
    CircleImageView circleImageView;
    Button save,cancel;
    int idLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);
        address=findViewById(R.id.address_pro);
        firstName=findViewById(R.id.firstname_profile_edit);
        lastName=findViewById(R.id.lastname_profile_edit);
        save=findViewById(R.id.save_profile);
        cancel=findViewById(R.id.cancel_profile);
        phone=findViewById(R.id.phone_profile_edit);
        circleImageView=findViewById(R.id.prof_img);
         profileInfo= (ProfileInfo) getIntent().getSerializableExtra("profileInfo");

        getData();

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingProfileActivity.this,Address_list_Activity.class);
                startActivityForResult(intent,100);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

    }

    private void getData()
    {

            if (profileInfo.getDataProfile().getFirst_name() != null) {
                firstName.setText(profileInfo.getDataProfile().getFirst_name());
            }
            if (profileInfo.getDataProfile().getLast_name() != null) {
                lastName.setText(profileInfo.getDataProfile().getLast_name());
            }
            if (profileInfo.getDataProfile().getMobile() != null) {
                phone.setText(profileInfo.getDataProfile().getMobile());
            }
            if(profileInfo.getDataProfile().getAvatar() !=null){
                Picasso.with(this).
                        load(profileInfo.getDataProfile().getAvatar())
                        .error(R.drawable.profile_img)
                        .into(circleImageView);
            }

            if (profileInfo.getDataProfile().getAddressProfile() != null) {
                address.setText(profileInfo.getDataProfile().getAddressProfile().getTitle());
            }

    }

    private void update (){
        String token = getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).getString(Constaints.TOKEN, "");
        if (token == null || token.isEmpty()) {
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(SettingProfileActivity.this, LoginActivity.class));
                            finish();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }
        else{
            String url = Constaints.URL_USED_FOR_SALES;
            ApiRetrofit apiRetrofit = APIManager.getInstance(url);
            showProgressBar();
            apiRetrofit.updateAddress("Bearer "+token,Constaints.LANG,firstName.getText().toString(),lastName.getText().toString(),idLocation).enqueue(new Callback<AddAddressInfo>() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==100 && resultCode==RESULT_OK){
            String titlelocation=data.getStringExtra("titlelocation");
            idLocation=data.getIntExtra("idlocation",0);
            address.setText(titlelocation);
        }
    }
}
