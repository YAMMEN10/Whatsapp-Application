package com.example.whatsapp.ui.Activitys.ProfileActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityProfileBinding activity_profile_binding;
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
        this.context = ProfileActivity.this;
        this.user_id = getIntent().getExtras().getBundle("data").getString("user id");
        $_Utils.makeToast(context, "dddd " + user_id, Toast.LENGTH_LONG);
    }

    @Override
    public void initializationActions() {

    }
}
