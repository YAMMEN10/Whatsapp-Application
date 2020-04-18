package com.example.whatsapp.ui.Activitys.SigninPhoneNumberActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.databinding.ActivitySigninPhoneNumberBinding;
import com.example.whatsapp.ui.Activitys.MainActivity.MainActivity;

public class SigninPhoneNumberActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private SigninPhoneNumberActivity context;
    private ActivitySigninPhoneNumberBinding activity_signin_phone_number_binding;
    private SigninPhoneNumberViewModel signin_phone_number_view_model;
    private ProgressDialog progress_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }


    @Override
    public void initializationView() {
        this.activity_signin_phone_number_binding = ActivitySigninPhoneNumberBinding.inflate(getLayoutInflater());
        View view = this.activity_signin_phone_number_binding.getRoot();
        setContentView(view);
        this.context = SigninPhoneNumberActivity.this;
        this.signin_phone_number_view_model = ViewModelProviders.of(context).get(SigninPhoneNumberViewModel.class);
        this.progress_dialog = $_Utils.makeProgressDialog(context);

        this.activity_signin_phone_number_binding.signinSigninPhoneSignin.setOnClickListener(this);
        this.activity_signin_phone_number_binding.signinPhoneNumberVerifyCode.setOnClickListener(this);

    }

    @Override
    public void initializationActions() {
        this.signin_phone_number_view_model.getLive_data_signin_phone_number().observe(context, new Observer<Pair<Integer, String>>() {
            @Override
            public void onChanged(Pair<Integer, String> data) {
                $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                progress_dialog.dismiss();
                if (data.first ==1){
                    $_Utils.goToTargetActivity(context, MainActivity.class);
                }
                if(data.first == 2){
                    progress_dialog.show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_signin_phone_signin:
                boolean not_empty_validate_phone_number = new $_NotEmptyValidator(activity_signin_phone_number_binding.signinPhoneNumber).validate();
                if(not_empty_validate_phone_number){
                    signin_phone_number_view_model.signinWithPhoneNumber(context, activity_signin_phone_number_binding.signinPhoneNumber.getText().toString());
                    progress_dialog.show();
                }else{

                }
                break;
            case R.id.signin_phone_number_verify_code:
                boolean not_empty_validate_verification_code = new $_NotEmptyValidator(activity_signin_phone_number_binding.signinPhoneNumber).validate();
                if(not_empty_validate_verification_code){
//                    signin_phone_number_view_model.verificationCode(activity_signin_phone_number_binding.signinPhoneVerificationCode.getText().toString());
                    progress_dialog.show();
                }else{

                }

        }
    }
}
