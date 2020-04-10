package com.example.whatsapp.ui.SigninActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.ActivitySigninBinding;
import com.example.whatsapp.ui.SignupActivity.SignupActivity;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_Utils;

public class SigninActivity extends AppCompatActivity implements $_InitializationView {
    private ActivitySigninBinding activity_signin_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity_signin_binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = this.activity_signin_binding.getRoot();
        setContentView(view);
        this.initializationView();
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (this.firebase_controller.getFirebase_user() == null) {
//            $Utils.go_to_target_activity(LoginActivity.this, MainActivity.class);
//        }
    }

    @Override
    public void initializationView() {
//        this.$signin_sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                $_Utils.go_to_target_activity(SigninActivity.this, SignupActivity.class);
//            }
//        });
//
//        this.$signin_sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean is_valid = true;
//                if (!Patterns.EMAIL_ADDRESS.matcher($signin_email.getText()).matches() || $signin_email.getText().toString().isEmpty()) {
//                    $signin_email.setError("Enter a valid email");
//                    is_valid = false;
//                }
//                if ($signin_password.getText().length() < 6) {
//                    $signin_password.setError("Password must larger than 6 character");
//                    is_valid = false;
//                }
//                if (is_valid) {
//
//                } else {
//                    $_Utils.makeToast(SigninActivity.this, "Error when trying to signip...", Toast.LENGTH_LONG);
//                }
//            }
//        });
    }

    @Override
    public void initializationActions() {

    }
}
