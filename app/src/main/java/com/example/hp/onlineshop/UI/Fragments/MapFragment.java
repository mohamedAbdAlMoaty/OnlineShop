package com.example.hp.onlineshop.UI.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by HP on 1/30/2019.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

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
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);  // زراير الزووم
        mMap.setBuildingsEnabled(true); //3d

        // or using interface
        LatLng place = new LatLng(Double.parseDouble(mapListener.lat()),Double.parseDouble(mapListener.lang()));

        /*
        // or using bundle
        String lat=getArguments().getString("lat");
        String lag=getArguments().getString("lang");
        LatLng place = new LatLng(Double.parseDouble(lat),Double.parseDouble(lag));
        */

        mMap.addMarker(new MarkerOptions().position(place)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,7));  //   علشان لو عايز اعمل زووم على مكان لول ما افتح البرنامج

    }


    public void SetMapListner(MapListener mapListener ){
        this.mapListener=mapListener;
    }

    public interface MapListener{
        String lat();
        String lang();
    }
}
