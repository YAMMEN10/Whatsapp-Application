package com.example.whatsapp.ui.SettingActivity;

import android.content.DialogInterface;
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
import com.example.whatsapp.Utils.InputDialog;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.databinding.ActivitySettingBinding;
import com.example.whatsapp.model.$_UserModel;
import com.example.whatsapp.ui.MainActivity.MainActivity;

public class SettingActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivitySettingBinding activity_setting_binding;
    private SettingViewModel setting_view_model;
    private SettingActivity context;
    private InputDialog alert_dialog;


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

        this.activity_setting_binding.cardViewUsername.setOnClickListener(this);
        this.activity_setting_binding.cardViewStatus.setOnClickListener(this);

        this.setting_view_model.getSettingInformation();


    }

    @Override
    public void initializationActions() {
        this.setting_view_model.getLive_data_save_user_information().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> data) {
                if (data.first) {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                    $_Utils.goToTargetActivityWithFlag(context, MainActivity.class);
                } else {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                }
            }
        });

        this.setting_view_model.getLive_data_get_user_model().observe(context, new Observer<$_UserModel>() {
            @Override
            public void onChanged($_UserModel user_model) {
                if (user_model != null) {
                    activity_setting_binding.profileUsername.setText(user_model.getName());
                    activity_setting_binding.profileStatus.setText(user_model.getStatus());
                    activity_setting_binding.profileEmail.setText(user_model.getEmail());
                } else {
                    $_Utils.makeToast(context, "Error in fetching data, please try again...", Toast.LENGTH_LONG);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view_username:
                initialInputDialog("Username");
                break;
            case R.id.card_view_status:
                initialInputDialog("Status");
                break;
        }
    }


    public void initialInputDialog(String message) {
        alert_dialog = new InputDialog(context, message);

        alert_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        switch (message) {
            case "Username":
                alert_dialog.getInput().setText(activity_setting_binding.profileUsername.getText());
                alert_dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        boolean not_empty_validation = new $_NotEmptyValidator(alert_dialog.getInput()).validate();
                        if (not_empty_validation) {
                            String username = alert_dialog.getInput().getText().toString();
                            setting_view_model.saveUserNameInformation(username);
                        } else {
                            $_Utils.makeToast(context, "Username is required...", Toast.LENGTH_LONG);
                        }
                    }
                });
                break;

            case "Status":
                alert_dialog.getInput().setText(activity_setting_binding.profileStatus.getText());
                alert_dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String status = alert_dialog.getInput().getText().toString();
                        setting_view_model.saveUserStatusInformation(status);
                    }
                });

        }
        alert_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

            }
        });

        alert_dialog.show();
    }
}
