package com.example.whatsapp.ui.GroupChatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.databinding.ActivityGroupChatBinding;
import com.example.whatsapp.model.$_MessageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupChatActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivityGroupChatBinding activity_group_chat_binding;
    private $_GroupChatViewModel group_chat_view_model;
    private GroupChatActivity context;
    private String group_name, current_username, current_date, current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        group_chat_view_model.getMessageOfGroup(group_name);
    }

    @Override
    public void initializationView() {

        this.activity_group_chat_binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        View view = this.activity_group_chat_binding.getRoot();
        setContentView(view);
        this.context = GroupChatActivity.this;

        this.group_chat_view_model = ViewModelProviders.of(context).get($_GroupChatViewModel.class);
        this.group_chat_view_model.getCurrentUsername();

        setSupportActionBar(this.activity_group_chat_binding.mainAppbar.mainAppbar);
        this.group_name = getIntent().getExtras().getBundle("group_data").getString("group_name");

        activity_group_chat_binding.sendMessageBottomLayout.bottomSendMessageSendMessage.setOnClickListener(this);
    }

    @Override
    public void initializationActions() {
        this.group_chat_view_model.getLive_data_username().observe(context, new Observer<String>() {
            @Override
            public void onChanged(String username) {
                if (username != null) {
                    current_username = username;
                } else {
                    $_Utils.makeToast(context, "Error in fetching current username", Toast.LENGTH_LONG);
                }
            }
        });

        this.group_chat_view_model.getLive_data_messages_of_group().observe(context, new Observer<$_MessageModel>() {
            @Override
            public void onChanged($_MessageModel message_model) {
                activity_group_chat_binding.messagesOfGroup.append(message_model.getName() + "\n" +
                        message_model.getMessage() + "\n" + message_model.getDate() + " \n" +
                        message_model.getTime());
                activity_group_chat_binding.groupChatScrollView.fullScroll(ScrollView.FOCUS_DOWN);

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_send_message_send_message:
                current_date = $_Utils.getDate();
                current_time = $_Utils.getTime();
                String message = activity_group_chat_binding.sendMessageBottomLayout.bottomSendMessageMessageInput.getText().toString();
                $_MessageModel message_model = new $_MessageModel(current_username, message, current_date, current_time);
                group_chat_view_model.saveMessageGroup(group_name, message_model.toMap());
                activity_group_chat_binding.sendMessageBottomLayout.bottomSendMessageMessageInput.setText("");
                activity_group_chat_binding.groupChatScrollView.fullScroll(ScrollView.FOCUS_DOWN);

        }
    }
}
