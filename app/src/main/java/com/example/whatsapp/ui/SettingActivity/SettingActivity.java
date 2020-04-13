package com.example.whatsapp.ui.SettingActivity;

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
import com.example.whatsapp.databinding.ActivitySettingBinding;
import com.example.whatsapp.ui.MainActivity.MainActivity;

public class SettingActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivitySettingBinding activity_setting_binding;
    private SettingViewModel setting_view_model;
    private SettingActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializationView();
        initializationActions();
    }


    @Override
    public void initializationView() {
        this.activity_setting_binding = ActivitySettingBinding.inflate(getLayoutInflater());
        View view = this.activity_setting_binding.getRoot();
        setContentView(view);

        this.context = SettingActivity.this;

        this.setting_view_model = ViewModelProviders.of(this).get(SettingViewModel.class);

        this.activity_setting_binding.profileUpdateInformation.setOnClickListener(this);
    }

    @Override
    public void initializationActions() {
    this.setting_view_model.getLive_data_save_user_information().observe(context, new Observer<Pair<Boolean, String>>() {
        @Override
        public void onChanged(Pair<Boolean, String> data) {
            if(data.first){
                $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                $_Utils.goToTargetActivityWithFlag(context, MainActivity.class);
            }else{
                $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
            }
        }
    });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_update_information:
                boolean not_empty_validation = new $_NotEmptyValidator(this.activity_setting_binding.profileInputUsername).validate();
                if (not_empty_validation) {
                    String username = this.activity_setting_binding.profileInputUsername.getText().toString();
                    String status = this.activity_setting_binding.profileInputStatus.getText().toString();
                    this.setting_view_model.saveUserInformation(username, status);
                } else {
                    $_Utils.makeToast(context, "Username is required...", Toast.LENGTH_LONG);
                }
        }
    }
}
