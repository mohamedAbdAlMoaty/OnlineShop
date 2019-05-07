package com.example.hp.onlineshop.UI.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.hp.onlineshop.R;
import com.example.hp.onlineshop.UI.Bases.BaseActivity;

import java.util.Locale;

public class EditProfileActivity extends BaseActivity {

    Toolbar toolbar;
    TextView title,language;
    LinearLayout linearLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar=findViewById(R.id.toolbar_edit);
        linearLayout=findViewById(R.id.all);
        setSupportActionBar(toolbar);
        language=findViewById(R.id.language);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbar.findViewById(R.id.toolbar_title_edit);
        title.setText("PAZ");
        language.setText(getResources().getString(R.string.lang));

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog();
            }
        });
    }

    private void getDialog(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.language_dialog);
        Button cancel=dialog.findViewById(R.id.cancel_dialog);
        RadioGroup radioGroup=dialog.findViewById(R.id.group_btn);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=group.findViewById(checkedId);


                if(radioButton.getText().toString().equals(getResources().getString(R.string.arabic_lang))){
                    setLocale("ar");
                    language.setText(getResources().getString(R.string.arabic_lang));

                }else if(radioButton.getText().toString().equals(getResources().getString(R.string.english_lang))){
                    setLocale("en");
                    language.setText(getResources().getString(R.string.english_lang));
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();




    }

    private  void setLocale(String lang){
        Locale myLocate=new Locale(lang);
        Resources res=getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        Configuration conf=res.getConfiguration();
        conf.locale=myLocate;
        res.updateConfiguration(conf,dm);
    }
}
