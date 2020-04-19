package com.example.whatsapp.ui.Activitys.SettingActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_InputDialog;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.databinding.ActivitySettingBinding;
import com.example.whatsapp.model.$_UserModel;
import com.example.whatsapp.ui.Activitys.MainActivity.MainActivity;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SettingActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivitySettingBinding activity_setting_binding;
    private $_SettingViewModel setting_view_model;
    private SettingActivity context;
    private $_InputDialog alert_dialog;
    public static int SELECT_IMAGE = 1;
    private ProgressDialog progress_dialog;


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

        this.setting_view_model = ViewModelProviders.of(this).get($_SettingViewModel.class);

        this.progress_dialog = $_Utils.makeProgressDialog(context, "Uploading image", "Please wait, while upload image for you...");

        this.activity_setting_binding.cardViewUsername.setOnClickListener(this);
        this.activity_setting_binding.cardViewStatus.setOnClickListener(this);
        this.activity_setting_binding.profileImageSelect.setOnClickListener(this);
        this.setting_view_model.getSettingInformation();


    }

    @Override
    public void initializationActions() {
        this.setting_view_model.getLive_data_save_user_name().observe(context, new Observer<Pair<Boolean, String>>() {
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

        this.setting_view_model.getLive_data_save_user_status().observe(context, new Observer<Pair<Boolean, String>>() {
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

        this.setting_view_model.getLive_data_save_user_image().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> data) {
                if (data.first) {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                    progress_dialog.dismiss();
                } else {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                }
            }
        });

        this.setting_view_model.getLive_data_save_user_image_to_storage().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> data) {
                if (data.first) {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                    setting_view_model.saveUserImage(data.second);
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
                    Picasso.get().load(user_model.getImage()).into(activity_setting_binding.profileImage);
                } else {
                    $_Utils.makeToast(context, "Error in fetching data, please try again...", Toast.LENGTH_LONG);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SELECT_IMAGE  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            Uri ImageUri = data.getData();

            CropImage.activity(ImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK)
            {
                Uri image = result.getUri();
                progress_dialog.show();
                setting_view_model.saveUserImageToStorage(image);
            }
        }
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
            case R.id.profile_image_select:
                    $_Utils.openGallery(context, SELECT_IMAGE);
                break;
        }
    }


    public void initialInputDialog(String message) {
        alert_dialog = new $_InputDialog(context, message);

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
                            setting_view_model.saveUserName(username);
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
                        setting_view_model.saveUserStatus(status);
                    }
                });

        }
        alert_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });


        alert_dialog.show();
    }
}
