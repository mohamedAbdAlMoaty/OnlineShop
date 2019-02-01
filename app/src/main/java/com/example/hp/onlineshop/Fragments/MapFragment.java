package com.example.hp.onlineshop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp.onlineshop.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by HP on 1/30/2019.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lat;
    String lang;
    MapListener mapListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapListener.getPosition();
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);  // زراير الزووم
        mMap.setBuildingsEnabled(true); //3d
        // Add a marker in Sydney and move the camera
        LatLng place = new LatLng(Double.parseDouble(lat),Double.parseDouble(lang));
        mMap.addMarker(new MarkerOptions().position(place)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,7));  //   علشان لو عايز اعمل زووم على مكان لول ما افتح البرنامج

    }


    public void SetMapListner(MapListener mapListener ){
        this.mapListener=mapListener;
    }

    public void setPosition(String lat ,String lang ){
        this.lang=lang;
        this.lat=lat;
    }

    public interface MapListener{
        void getPosition();
    }
}
