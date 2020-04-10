package com.example.whatsapp.ui.SignupActivity;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.Utils.Validation.$_EmailValidator;
import com.example.whatsapp.Utils.Validation.$_GreaterThanValidator;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.databinding.ActivitySignupBinding;
import com.example.whatsapp.model.$_UserModel;
import com.example.whatsapp.ui.SigninActivity.SigninActivity;

public class SignupActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivitySignupBinding activity_signup_binding;
    private ProgressDialog progress_dialog;
    private $_SignupViewModel signup_view_model;
    private SignupActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.context = SignupActivity.this;
    }


    @Override
    public void initializationView() {
        this.activity_signup_binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = this.activity_signup_binding.getRoot();
        setContentView(view);

        this.progress_dialog = new ProgressDialog(this);
        progress_dialog.setTitle("Creating account");
        progress_dialog.setMessage("Please wait, while creating new account for you...");
        progress_dialog.setCanceledOnTouchOutside(true);

        this.signup_view_model = ViewModelProviders.of(this).get($_SignupViewModel.class);
//        this.$signup_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                $_Utils.go_to_target_activity(SignupActivity.this, SigninActivity.class);
//            }
//        });
//

    }

    @Override
    public void initializationActions() {
        signup_view_model.getLive_data_user_model().observe(this, new Observer<$_UserModel>() {
            @Override
            public void onChanged(@Nullable $_UserModel userModel) {
                progress_dialog.dismiss();
                if (userModel != null) {
                    $_Utils.makeToast(context, "Account created successful", Toast.LENGTH_LONG);
                    $_Utils.go_to_target_activity(context, SigninActivity.class);
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
                } else {
                    $_Utils.makeToast(context, "Account created unsuccessful", Toast.LENGTH_LONG);
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_signup:
                boolean email_validate = new $_EmailValidator(activity_signup_binding.signupEmail).validate();
                boolean not_empty_validate = new $_NotEmptyValidator(activity_signup_binding.signupEmail).validate();
                boolean greater_than = new $_GreaterThanValidator(activity_signup_binding.signupPassword, 6).validate();
                if (email_validate && not_empty_validate && greater_than) {
                    String email = activity_signup_binding.signupEmail.getText().toString().trim();
                    String password = activity_signup_binding.signupPassword.getText().toString().trim();
                    progress_dialog.show();
                    signup_view_model.createAccountByGmail(email, password);
                } else {
                    $_Utils.makeToast(context, "Error when trying to signup...", Toast.LENGTH_LONG);
                }
                break;
            case R.id.signup_signin:
                break;
            default:
                $_Utils.makeToast(context, "No any element founded with id : " + String.valueOf(view.getId()), Toast.LENGTH_LONG);
        }
    }
}
