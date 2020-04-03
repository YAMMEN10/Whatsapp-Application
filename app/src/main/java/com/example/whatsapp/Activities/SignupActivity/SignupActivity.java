package com.example.whatsapp.Activities.SignupActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatsapp.Activities.SigninActivity.SigninActivity;
import com.example.whatsapp.Activities.UtilsActivity.$UtilsActivity;
import com.example.whatsapp.Firebase.$FirebaseController;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends AppCompatActivity implements $UtilsActivity {
    private EditText $signup_email;
    private EditText $signup_password;
    private Button $signup_signup;
    private Button $signup_signin;
    private $FirebaseController firebase_controller;
    ProgressDialog $progress_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.initializationAllViews();
        this.initializationActionOfViews();
    }

    @Override
    public void initializationAllViews() {
        this.$signup_email = findViewById(R.id.signup_email);
        this.$signup_password = findViewById(R.id.signup_password);
        this.$signup_signup = findViewById(R.id.signup_signup);
        this.$signup_signin = findViewById(R.id.signup_signin);
        this.firebase_controller = new $FirebaseController();
        this.$progress_dialog = new ProgressDialog(this);
    }

    @Override
    public void initializationActionOfViews() {
        this.$signup_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                $Utils.go_to_target_activity(SignupActivity.this, SigninActivity.class);
            }
        });

        this.$signup_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_valid = true;
                if (!Patterns.EMAIL_ADDRESS.matcher($signup_email.getText()).matches() || $signup_email.getText().toString().isEmpty()) {
                    $signup_email.setError("Enter a valid email");
                    is_valid = false;
                }
                if ($signup_password.getText().length() < 6) {
                    $signup_password.setError("Password must larger than 6 character");
                    is_valid = false;
                }
                if (is_valid) {
                    String email = $signup_email.getText().toString().trim();
                    String password = $signup_password.getText().toString().trim();
                    Task result = firebase_controller.createAccountByGmail(email, password);
                    $progress_dialog.setTitle("Creating account");
                    $progress_dialog.setMessage("Please wait, while creating new account for you...");
                    $progress_dialog.setCanceledOnTouchOutside(true);
                    $progress_dialog.show();
                    result.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                $progress_dialog.dismiss();
                                $Utils.makeToast(SignupActivity.this, "Account created successful", Toast.LENGTH_LONG);
                                $Utils.go_to_target_activity(SignupActivity.this, SigninActivity.class);
                            } else {
                                $progress_dialog.dismiss();
                                $Utils.makeToast(SignupActivity.this, "Account created unsuccessful", Toast.LENGTH_LONG);
                            }
                        }
                    });


                } else {
                    $Utils.makeToast(SignupActivity.this, "Error when trying to signup...", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
