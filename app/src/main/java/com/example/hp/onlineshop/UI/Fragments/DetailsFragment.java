package com.example.hp.onlineshop.UI.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseFragment;

/**
 * Created by HP on 1/30/2019.
 */

public class DetailsFragment extends BaseFragment {

    TextView textView;
    DetailsListener detailsListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.details_fragment, container, false);
        textView=rootView.findViewById(R.id.details_used_for_sales);
        //or using interface
        textView.setText( detailsListener.getDetails());

        /*
        // or using bundle
        String details=getArguments().getString("details");
        textView.setText( details);
        */


        return rootView;
    }

    public void SetListner(DetailsListener detailsListener){
        this.detailsListener=detailsListener;
    }

    public interface DetailsListener{
        String getDetails();
    }

}
