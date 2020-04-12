package com.example.whatsapp.ui.MainActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.example.whatsapp.ui.MainActivity.Adapter.$_TabsAccessAdapter;
import com.example.whatsapp.ui.SettingActivity.SettingActivity;
import com.example.whatsapp.ui.SigninActivity.SigninActivity;
import com.example.whatsapp.ui.SignupActivity.SignupActivity;

public class MainActivity extends AppCompatActivity implements $_InitializationView {
    private ActivityMainBinding activity_main_binding;
    private MainViewModel main_view_model;
    private $_TabsAccessAdapter tabs_access_adapter;
    private MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if ($_FirebaseData.getINSTANCE().getFirebase_user() == null) {
            $_Utils.goToTargetActivity(context, SignupActivity.class);
        }

    }

    @Override
    public void initializationView() {
        this.activity_main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.activity_main_binding.getRoot();
        setContentView(view);

        this.context = MainActivity.this;

        $_FirebaseData.getINSTANCE().setFirebase_user($_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser());

        this.main_view_model = ViewModelProviders.of(context).get(MainViewModel.class);

        setSupportActionBar(this.activity_main_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setTitle("Whatsapp");
        this.tabs_access_adapter = new $_TabsAccessAdapter(getSupportFragmentManager(), 3);
        activity_main_binding.mainTabsViewPage.setAdapter(this.tabs_access_adapter);
        activity_main_binding.mainTapsLayout.setupWithViewPager(activity_main_binding.mainTabsViewPage);
    }

    @Override
    public void initializationActions() {
        this.main_view_model.getLive_data_logout().observe(context, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean sign_out) {
                if (sign_out) {
                    $_Utils.goToTargetActivity(context, SigninActivity.class);
                } else {
                    $_Utils.makeToast(context, "Error when try to signout account", Toast.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_find_friend_option:
                break;
            case R.id.main_settings_option:
                break;
            case R.id.main_logout_option:
                this.main_view_model.signOutAccount();
                break;
        }
        return true;
    }
}
