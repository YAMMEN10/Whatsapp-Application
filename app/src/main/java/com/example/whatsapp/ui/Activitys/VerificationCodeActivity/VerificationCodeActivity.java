package com.example.whatsapp.ui.Activitys.VerificationCodeActivity;

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
import com.example.whatsapp.databinding.ActivityVerificationCodeBinding;
import com.example.whatsapp.ui.Activitys.MainActivity.MainActivity;

public class VerificationCodeActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivityVerificationCodeBinding activity_verification_code_binding;
    private VerificationCodeViewModel verification_code_view_model;
    private VerificationCodeActivity context;
    private ProgressDialog progress_dialog;

    private String my_verification_id;
    private String phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();

    }

    @Override
    public void initializationView() {

        this.activity_verification_code_binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        View view = this.activity_verification_code_binding.getRoot();
        setContentView(view);

        this.context = VerificationCodeActivity.this;

        this.verification_code_view_model = ViewModelProviders.of(context).get(VerificationCodeViewModel.class);

        this.progress_dialog = $_Utils.makeProgressDialog(context, "Verification Mobile Number", "Please wait, while verification mobile number for you...");

        this.my_verification_id = getIntent().getExtras().getBundle("data").getString("my verification id");
        this.phone_number = getIntent().getExtras().getBundle("data").getString("phone number");

        this.activity_verification_code_binding.signinPhoneNumberVerifyCode.setOnClickListener(this);



    }

    @Override
    public void initializationActions() {
        this.verification_code_view_model.getLive_data_verification_code().observe(context, new Observer<Pair<Integer, String>>() {
            @Override
            public void onChanged(Pair<Integer, String> data) {
                $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                progress_dialog.dismiss();
                if (data.first == 1) {
                    $_Utils.goToTargetActivity(context, MainActivity.class);

                } else {

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_phone_number_verify_code:
                boolean not_empty_validate_verification_code = new $_NotEmptyValidator(activity_verification_code_binding.signinPhoneAddVerificationCode).validate();
                if (not_empty_validate_verification_code) {
                    verification_code_view_model.verificationCode(activity_verification_code_binding.signinPhoneAddVerificationCode.getText().toString(), my_verification_id, phone_number);
                    progress_dialog.show();
                } else {

                }
                break;
        }
    }
}
