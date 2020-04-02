package com.example.whatsapp.Activities.LoginActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.whatsapp.Firebase.$FirebaseController;
import com.example.whatsapp.MainActivity;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$Utils;

public class LoginActivity extends AppCompatActivity {
    private $FirebaseController firebase_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.firebase_controller = new $FirebaseController();

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (this.firebase_controller.getFirebase_user() == null) {
            $Utils.go_to_target_activity(LoginActivity.this, MainActivity.class);
        }
    }
}
