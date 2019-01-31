package com.example.hp.onlineshop.Activities;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.onlineshop.Bases.BaseActivity;
import com.example.hp.onlineshop.Fragments.DepartmentsFragment;
import com.example.hp.onlineshop.Fragments.HomeFragment;
import com.example.hp.onlineshop.R;

public class HomeActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    TextView title;
    String hallo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title=toolbar.findViewById(R.id.toolbar_title);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment fragment=null;

                        switch (item.getItemId()) {
                            case R.id.homeicon:
                                title.setText(R.string.home);
                                fragment=new HomeFragment();
                                break;
                            case R.id.departicon:
                                title.setText(R.string.departments);
                                fragment=new DepartmentsFragment();
                                break;
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content,fragment)
                                .commit();

                        return true;
                    }
                });

        //default clicked
        bottomNavigationView.setSelectedItemId(R.id.homeicon);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showConfirmationDialog("Warning", "do you want leave app",
                "confirm", "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                },new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }


}
