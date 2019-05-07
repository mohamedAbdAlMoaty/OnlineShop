package com.example.hp.onlineshop.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfileLocationActivity extends BaseActivity implements OnMapReadyCallback {

    Toolbar toolbar;
    TextView title,save,cancel;
    private GoogleMap mMap;
    LatLng place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_location);
        toolbar=findViewById(R.id.toolbar_location);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbar.findViewById(R.id.toolbar_title_location);
        save=toolbar.findViewById(R.id.toolbar_save_location);
        cancel=toolbar.findViewById(R.id.toolbar_cancel_location);
        title.setText("Select Location");
        save.setText("Save");
        cancel.setText("Cancel");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.location);
        mapFragment.getMapAsync(this);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(place !=null){
                    Intent intent=new Intent();
                    intent.putExtra("long",String.valueOf(place.longitude));
                    intent.putExtra("lat",String.valueOf(place.latitude));
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }

            }
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);  // زراير الزووم
        mMap.setBuildingsEnabled(true); //3d

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                place =latLng;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place));  //   علشان لو عايز اعمل زووم على مكان لول ما افتح البرنامج
            }
        });



    }
}
