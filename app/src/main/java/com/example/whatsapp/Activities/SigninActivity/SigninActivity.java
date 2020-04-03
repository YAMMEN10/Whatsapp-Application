package com.example.whatsapp.Activities.SigninActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.whatsapp.Activities.SignupActivity.SignupActivity;
import com.example.whatsapp.Activities.UtilsActivity.$UtilsActivity;
import com.example.whatsapp.Firebase.$FirebaseController;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$Utils;

public class SigninActivity extends AppCompatActivity implements $UtilsActivity {
    private $FirebaseController firebase_controller;
    private EditText $signin_email;
    private EditText $signin_password;
    private Button $signin_sign_in;
    private TextView $signin_forget_password;
    private TextView $signin_sign_up;
    private ImageButton $signin_signin_with_mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        this.initializationAllViews();
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        this.initializationAllViews();
        this.initializationActionOfViews();
//        if (this.firebase_controller.getFirebase_user() == null) {
//            $Utils.go_to_target_activity(LoginActivity.this, MainActivity.class);
//        }
    }

    @Override
    public void initializationAllViews() {
        this.firebase_controller = new $FirebaseController();
        this.$signin_email = findViewById(R.id.signin_email);
        this.$signin_password = findViewById(R.id.signin_password);
        this.$signin_sign_in = findViewById(R.id.signin_sign_in);
        this.$signin_forget_password = findViewById(R.id.signin_forget_password);
        this.$signin_sign_up = findViewById(R.id.signin_sign_up);
        this.$signin_signin_with_mobile = findViewById(R.id.signin_signin_with_mobile);
    }

    @Override
    public void initializationActionOfViews() {
        this.$signin_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                $Utils.go_to_target_activity(SigninActivity.this, SignupActivity.class);
            }
        });
    }
}
