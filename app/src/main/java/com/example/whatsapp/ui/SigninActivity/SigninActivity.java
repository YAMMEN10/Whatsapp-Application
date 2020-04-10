package com.example.whatsapp.ui.SigninActivity;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.Validation.$_EmailValidator;
import com.example.whatsapp.Utils.Validation.$_GreaterThanValidator;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.databinding.ActivitySigninBinding;
import com.example.whatsapp.model.$_UserModel;
import com.example.whatsapp.ui.MainActivity.MainActivity;
import com.example.whatsapp.ui.SignupActivity.SignupActivity;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_Utils;

public class SigninActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivitySigninBinding activity_signin_binding;
    private ProgressDialog progress_dialog;
    private $_SigninViewModel signin_view_model;
    private SigninActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
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
        this.context = SigninActivity.this;

        this.activity_signin_binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = this.activity_signin_binding.getRoot();
        setContentView(view);

        this.progress_dialog = new ProgressDialog(context);
        progress_dialog.setTitle("Creating account");
        progress_dialog.setMessage("Please wait, while creating new account for you...");
        progress_dialog.setCanceledOnTouchOutside(true);
        this.signin_view_model = ViewModelProviders.of(context).get($_SigninViewModel.class);


        this.activity_signin_binding.signinSignIn.setOnClickListener(this);
        this.activity_signin_binding.signinSignUp.setOnClickListener(this);
    }

    @Override
    public void initializationActions() {
        signin_view_model.getLive_data_user_model().observe(context, new Observer<$_UserModel>() {
            @Override
            public void onChanged(@Nullable $_UserModel userModel) {
                progress_dialog.dismiss();
                if (userModel != null) {
                    $_Utils.makeToast(context, "Account signin successful", Toast.LENGTH_LONG);
                    $_Utils.go_to_target_activity(context, MainActivity.class);
                    System.out.println("ttttttttttttttttttttttttttt");
                } else {
                    $_Utils.makeToast(context, "Account signin unsuccessful", Toast.LENGTH_LONG);

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin_sign_in:
                boolean email_validate = new $_EmailValidator(activity_signin_binding.signinEmail).validate();
                boolean not_empty_validate = new $_NotEmptyValidator(activity_signin_binding.signinEmail).validate();
                boolean greater_than = new $_GreaterThanValidator(activity_signin_binding.signinPassword, 6).validate();
                if (email_validate && not_empty_validate && greater_than) {
                    String email = activity_signin_binding.signinEmail.getText().toString().trim();
                    String password = activity_signin_binding.signinPassword.getText().toString().trim();
                    progress_dialog.show();
                    signin_view_model.signinAccountByGmail(email, password);
                } else {
                    $_Utils.makeToast(context, "Error when trying to signip...", Toast.LENGTH_LONG);
                }
                break;
            case R.id.signin_sign_up:
                $_Utils.go_to_target_activity(SigninActivity.this, SignupActivity.class);
                break;
        }
    }
}
