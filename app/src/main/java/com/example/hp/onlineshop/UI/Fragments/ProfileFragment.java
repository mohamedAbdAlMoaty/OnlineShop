package com.example.hp.onlineshop.UI.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.onlineshop.Model.API.APIManager;
import com.example.hp.onlineshop.Model.API.ApiRetrofit;
import com.example.hp.onlineshop.Model.DataModel.HotOfferResponse;
import com.example.hp.onlineshop.Model.DataModel.ProfileInfo;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Activities.Address_list_Activity;
import com.example.hp.onlineshop.UI.Activities.EditProfileActivity;
import com.example.hp.onlineshop.UI.Activities.LoginActivity;
import com.example.hp.onlineshop.UI.Activities.SettingProfileActivity;
import com.example.hp.onlineshop.UI.Adapter.LikesAdapter;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;
import com.example.hp.onlineshop.Utils.Constaints;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HP on 3/1/2019.
 */

public class ProfileFragment extends BaseFragment {

    CircleImageView circleImageView;
    TextView phone_number,firstName,lastName,address;
    ImageView setting,edit;
    ProfileInfo profileInfo;
    Intent intent;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        circleImageView=rootView.findViewById(R.id.profile_image);
        setting=rootView.findViewById(R.id.setting_profile);
        edit=rootView.findViewById(R.id.edit_profile);
        phone_number=rootView.findViewById(R.id.phone_profile);
        firstName=rootView.findViewById(R.id.firstname_profile);
        lastName=rootView.findViewById(R.id.lastname_profile);
        address=rootView.findViewById(R.id.address_profile);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profileInfo !=null){
                    intent=new Intent(getActivity(), SettingProfileActivity.class);
                    intent.putExtra("profileInfo", profileInfo);
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {


        String token = getContext().getSharedPreferences(Constaints.SHARED_PREFRE, MODE_PRIVATE).
                getString(Constaints.TOKEN,"");
        if(token == null||token.isEmpty()){
            showConfirmationDialog("error", "please login first", "ok", "cancel"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
        }
    }, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
        }
        else{
            String url=Constaints.URL_HOME_DEPART_FRAGMENT;
            ApiRetrofit apiRetrofit= APIManager.getInstance(url);
            showProgressBar();
            apiRetrofit.getProfile("Bearer "+token,Constaints.LANG).enqueue(new Callback<ProfileInfo>() {
                @Override
                public void onResponse(Call<ProfileInfo> call, Response<ProfileInfo> response) {
                    hideProgressBar();
                     profileInfo = response.body();

                    if(profileInfo.getDataProfile().getAvatar() !=null){
                        Picasso.with(getActivity()).load(profileInfo.getDataProfile().getAvatar()).into(circleImageView);
                    }
                    if(profileInfo.getDataProfile().getFirst_name() !=null){
                        firstName.setText(profileInfo.getDataProfile().getFirst_name());
                    }
                    if(response.body().getDataProfile().getLast_name() !=null){
                        lastName.setText(response.body().getDataProfile().getLast_name());
                    }
                    if(response.body().getDataProfile().getMobile() !=null){
                        phone_number.setText(response.body().getDataProfile().getMobile());
                    }
                    if(response.body().getDataProfile().getAddressProfile() !=null&&profileInfo.getDataProfile().getAddressProfile().getTitle()!=null){

                        address.setText(response.body().getDataProfile().getAddressProfile().getTitle());
                    }


                }

                @Override
                public void onFailure(Call<ProfileInfo> call, Throwable t) {

                }
            });

        }


    }
}
