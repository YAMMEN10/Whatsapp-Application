package com.example.whatsapp.ui.GroupChatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.ActivityGroupChatBinding;

public class GroupChatActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityGroupChatBinding activity_group_chat_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    public void initializationView() {
        this.activity_group_chat_binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        View view = this.activity_group_chat_binding.getRoot();
        setContentView(view);
        setSupportActionBar(this.activity_group_chat_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setTitle(getIntent().getExtras().getBundle("group_data").getString("group_name"));
    }

    @Override
    public void initializationActions() {

    }
}
