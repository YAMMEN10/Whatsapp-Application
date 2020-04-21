package com.example.whatsapp.ui.Activitys.FindFriendActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.ActivityFindFriendBinding;
import com.example.whatsapp.ui.Activitys.FindFriendActivity.Adapter.$_FindFriendAdapter;

public class FindFriendActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityFindFriendBinding activity_find_friend_binding;
    private $_FindFriendAdapter find_friend_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.find_friend_adapter = new $_FindFriendAdapter();
        this.activity_find_friend_binding.recyclerViewFriend.setAdapter(this.find_friend_adapter.getAdapter());
        this.activity_find_friend_binding.recyclerViewFriend.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this.activity_find_friend_binding.recyclerViewFriend.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider);
        divider.setDrawable(drawable);

        this.activity_find_friend_binding.recyclerViewFriend.addItemDecoration(divider);
        this.find_friend_adapter.getAdapter().startListening();
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
