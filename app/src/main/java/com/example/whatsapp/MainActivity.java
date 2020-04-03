package com.example.whatsapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.whatsapp.Activities.SigninActivity.SigninActivity;
import com.example.whatsapp.Activities.SignupActivity.SignupActivity;
import com.example.whatsapp.Activities.UtilsActivity.$UtilsActivity;
import com.example.whatsapp.Adapters.TabsAdapter.$TabsAccessAdapter;
import com.example.whatsapp.Firebase.$FirebaseController;
import com.example.whatsapp.Utils.$Utils;

public class MainActivity extends AppCompatActivity implements $UtilsActivity {
    private Toolbar $main_page_toolbar;
    private ViewPager $main_tabs_view_page;
    private TabLayout $main_taps_layout;
    private $TabsAccessAdapter $tabs_access_adapter;
    private $FirebaseController $firebase_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializationAllViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.$firebase_controller.getFirebase_user() == null) {
            $Utils.go_to_target_activity(MainActivity.this, SigninActivity.class);
        }
    }

    @Override
    public void initializationAllViews() {
        this.$main_page_toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(this.$main_page_toolbar);
        getSupportActionBar().setTitle("Whatsapp");
        this.$main_tabs_view_page = findViewById(R.id.main_tabs_view_page);
        this.$tabs_access_adapter = new $TabsAccessAdapter(getSupportFragmentManager(), 3);
        this.$main_tabs_view_page.setAdapter(this.$tabs_access_adapter);
        this.$main_taps_layout = findViewById(R.id.main_taps_layout);
        this.$main_taps_layout.setupWithViewPager(this.$main_tabs_view_page);
        this.$firebase_controller = new $FirebaseController();
    }

    @Override
    public void initializationActionOfViews() {

    }
}
