package com.example.whatsapp.ui.Activitys.FindFriendActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.ActivityFindFriendBinding;

public class FindFriendActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityFindFriendBinding activity_find_friend_binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    public void initializationView() {
        this.activity_find_friend_binding = ActivityFindFriendBinding.inflate(getLayoutInflater());
        View view = this.activity_find_friend_binding.getRoot();
        setContentView(view);

        setSupportActionBar(this.activity_find_friend_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Find Friend");
    }

    @Override
    public void initializationActions() {

    }
}
