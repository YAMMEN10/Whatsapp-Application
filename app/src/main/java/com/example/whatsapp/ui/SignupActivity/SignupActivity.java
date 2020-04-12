package com.example.whatsapp.ui.SignupActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.Utils.Validation.$_EmailValidator;
import com.example.whatsapp.Utils.Validation.$_GreaterThanValidator;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ActivitySignupBinding;
import com.example.whatsapp.model.$_UserModel;
import com.example.whatsapp.ui.MainActivity.MainActivity;
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
        if ($_FirebaseData.getINSTANCE().getFirebase_user() != null) {
            $_Utils.goToTargetActivity(context, MainActivity.class);
        }
    }


    @Override
    public void initializationView() {
        this.activity_signup_binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = this.activity_signup_binding.getRoot();
        setContentView(view);

        this.context = SignupActivity.this;


        $_FirebaseData.getINSTANCE().setFirebase_user($_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser());

        this.signup_view_model = ViewModelProviders.of(this.context).get($_SignupViewModel.class);

        this.progress_dialog = $_Utils.makeProgressDialog(context);

        this.activity_signup_binding.signupSignup.setOnClickListener(this);
        this.activity_signup_binding.signupSignin.setOnClickListener(this);
    }

    @Override
    public void initializationActions() {
        signup_view_model.getLive_data_user_model().observe(context, new Observer<$_UserModel>() {
            @Override
            public void onChanged(@Nullable $_UserModel userModel) {
                progress_dialog.dismiss();
                if (userModel != null) {
                    $_Utils.makeToast(context, "Account created successful", Toast.LENGTH_LONG);
                    $_Utils.goToTargetActivityWithFlag(context, MainActivity.class);
                } else {
                    $_Utils.makeToast(context, "Account created unsuccessful", Toast.LENGTH_LONG);
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
                $_Utils.goToTargetActivity(SignupActivity.this, SigninActivity.class);
                break;
            default:
                $_Utils.makeToast(context, "No any element founded with id : " + String.valueOf(view.getId()), Toast.LENGTH_LONG);
        }
    }
}
