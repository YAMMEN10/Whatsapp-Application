package com.example.whatsapp.Activities.SignupActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.whatsapp.Activities.SigninActivity.SigninActivity;
import com.example.whatsapp.Activities.UtilsActivity.$UtilsActivity;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$Utils;

public class SignupActivity extends AppCompatActivity implements $UtilsActivity {
    private EditText $signup_email;
    private EditText $signup_password;
    private Button $signup_signup;
    private Button $signup_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public void initializationAllViews() {
        this.$signup_email = findViewById(R.id.signup_email);
        this.$signup_password = findViewById(R.id.signup_password);
        this.$signup_signup = findViewById(R.id.signup_signup);
        this.$signup_signin = findViewById(R.id.signup_signin);
    }

    @Override
    public void initializationActionOfViews() {
        this.$signup_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                $Utils.go_to_target_activity(SignupActivity.this, SigninActivity.class);
            }
        });
    }
}
