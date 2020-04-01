package com.example.whatsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar main_page_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.main_page_toolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(this.main_page_toolbar);
        getSupportActionBar().setTitle("Whatsapp");
    }
}
