package com.example.whatsapp.ui.Activitys.ProfileActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.ActivityProfileBinding;
import com.example.whatsapp.model.$_UserModel;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityProfileBinding activity_profile_binding;
    private $_ProfileActivityViewModel profile_activity_view_model;
    private String user_id;
    private ProfileActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    public void initializationView() {
        this.activity_profile_binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = this.activity_profile_binding.getRoot();
        setContentView(view);
        this.activity_profile_binding.showProfileUsername.setMovementMethod(new ScrollingMovementMethod());
        this.activity_profile_binding.showProfileStatus.setMovementMethod(new ScrollingMovementMethod());
        this.context = ProfileActivity.this;
        this.user_id = getIntent().getExtras().getBundle("data").getString("user id");

        this.profile_activity_view_model = ViewModelProviders.of(context).get($_ProfileActivityViewModel.class);

        setSupportActionBar(this.activity_profile_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");

        this.profile_activity_view_model.getUserInformation(user_id);
    }

    @Override
    public void initializationActions() {
        this.profile_activity_view_model.getLive_data_user_information().observe(context, new Observer<$_UserModel>() {
            @Override
            public void onChanged($_UserModel user_model) {
                if (user_model != null) {
                    activity_profile_binding.showProfileUsername.setText(user_model.getName());
                    activity_profile_binding.showProfileStatus.setText(user_model.getStatus());
                    activity_profile_binding.showProfileEmail.setText(user_model.getEmail());
                    try {
                        Picasso.get().load(user_model.getImage()).placeholder(R.drawable.default_image).into(activity_profile_binding.showProfileImage);
                    } catch (Exception ex) {

                    }

                }
            }
        });
    }
}
