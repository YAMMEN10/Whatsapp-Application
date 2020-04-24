package com.example.whatsapp.ui.Activitys.ProfileActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ActivityProfileBinding;
import com.example.whatsapp.model.$_UserModel;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivityProfileBinding activity_profile_binding;
    private $_ProfileActivityViewModel profile_activity_view_model;
    private String sender_user_id, receiver_user_id;
    private ProfileActivity context;
    private String current_state;
    private ProgressDialog progress_dialog;


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

        this.progress_dialog = $_Utils.makeProgressDialog(context, "Profile Information","Please wait, while getting profile information for you...");
        this.progress_dialog.show();

        this.activity_profile_binding.showProfileUsername.setMovementMethod(new ScrollingMovementMethod());
        this.activity_profile_binding.showProfileStatus.setMovementMethod(new ScrollingMovementMethod());
        this.receiver_user_id = getIntent().getExtras().getBundle("data").getString("user id");
        this.sender_user_id = $_FirebaseData.getINSTANCE().getFirebase_user().getUid();

        this.profile_activity_view_model = ViewModelProviders.of(context).get($_ProfileActivityViewModel.class);

        setSupportActionBar(this.activity_profile_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");

        this.activity_profile_binding.showProfileImage.setAnimating(false);
        if (this.receiver_user_id.equals(this.sender_user_id)) {
            this.activity_profile_binding.showProfileSendMessage.setVisibility(View.INVISIBLE);
        } else {

        }
        this.current_state = "new";

        this.activity_profile_binding.showProfileSendMessage.setOnClickListener(this);
        this.profile_activity_view_model.getMessageChatStateFromSenderToReceiver(sender_user_id, receiver_user_id);
        this.profile_activity_view_model.getUserInformation(receiver_user_id);
    }

    @Override
    public void initializationActions() {
        this.profile_activity_view_model.getLive_data_user_information().observe(context, new Observer<$_UserModel>() {
            @Override
            public void onChanged($_UserModel user_model) {
                progress_dialog.dismiss();
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

        this.profile_activity_view_model.getLive_data_receive_message_request().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                progress_dialog.dismiss();
                activity_profile_binding.showProfileSendMessage.setBackgroundResource(R.drawable.solid_button_cancel);
                activity_profile_binding.showProfileSendMessage.setText("Cancel Message Request");
            }
        });

        this.profile_activity_view_model.getLive_data_state_message_request().observe(context, new Observer<String>() {
            @Override
            public void onChanged(String state) {
                progress_dialog.dismiss();
                current_state = state;
                if (state.equals("sent")) {
                    activity_profile_binding.showProfileSendMessage.setBackgroundResource(R.drawable.solid_button_cancel);
                    activity_profile_binding.showProfileSendMessage.setText("Cancel Message Request");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_profile_send_message:
                if (current_state.equals("new")) {
                    this.progress_dialog = $_Utils.makeProgressDialog(context, "Message Request","Please wait, while sending message request for you...");
                    this.progress_dialog.show();                    this.activity_profile_binding.showProfileSendMessage.setEnabled(false);
                    this.profile_activity_view_model.sendChatRequest(this.sender_user_id, this.receiver_user_id);
                }
        }
    }
}
