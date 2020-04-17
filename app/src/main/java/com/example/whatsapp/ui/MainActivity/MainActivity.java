package com.example.whatsapp.ui.MainActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Pair;
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
import com.example.whatsapp.Utils.$_InputDialog;
import com.example.whatsapp.Utils.Validation.$_NotEmptyValidator;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.example.whatsapp.ui.GroupChatActivity.GroupChatActivity;
import com.example.whatsapp.ui.MainActivity.Adapter.$_TabsAccessAdapter;
import com.example.whatsapp.ui.SettingActivity.SettingActivity;
import com.example.whatsapp.ui.SigninActivity.SigninActivity;
import com.example.whatsapp.ui.SignupActivity.SignupActivity;

public class MainActivity extends AppCompatActivity implements $_InitializationView, View.OnClickListener {
    private ActivityMainBinding activity_main_binding;
    private $_MainViewModel main_view_model;
    private $_TabsAccessAdapter tabs_access_adapter;
    private MainActivity context;
    private $_InputDialog alert_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationView();
        this.initializationActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if ($_FirebaseData.getINSTANCE().getFirebase_user() == null) {
            $_Utils.goToTargetActivity(context, GroupChatActivity.class);
//        } else {
//            this.main_view_model.checkUsernameIsExist();
//        }

    }

    @Override
    public void initializationView() {
        this.activity_main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.activity_main_binding.getRoot();
        setContentView(view);

        this.context = MainActivity.this;

        $_FirebaseData.getINSTANCE().setFirebase_user($_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser());

        this.main_view_model = ViewModelProviders.of(context).get($_MainViewModel.class);

        setSupportActionBar(this.activity_main_binding.mainAppbar.mainAppbar);
        getSupportActionBar().setTitle("Whatsapp");
        this.tabs_access_adapter = new $_TabsAccessAdapter(getSupportFragmentManager(), 3);
        activity_main_binding.mainTabsViewPage.setAdapter(this.tabs_access_adapter);
        activity_main_binding.mainTapsLayout.setupWithViewPager(activity_main_binding.mainTabsViewPage);
        this.activity_main_binding.mainItemFloatingActionMenu.setOnClickListener(this);
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

        this.main_view_model.getLive_data_check_username_is_exist().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> data) {
                if (data.first) {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                } else {
                    $_Utils.goToTargetActivityWithFlag(context, SettingActivity.class);
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                }
            }
        });


        this.main_view_model.getLive_data_create_group().observe(context, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> data) {
                if (data.first) {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
                } else {
                    $_Utils.makeToast(context, data.second, Toast.LENGTH_LONG);
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
                $_Utils.goToTargetActivity(context, SettingActivity.class);
                break;
            case R.id.main_logout_option:
                this.main_view_model.signOutAccount();
                break;
        }
        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_item_floating_action_menu:
                initialInputDialog("Group name");
                activity_main_binding.mainFloatingActionMenu.close(true);
        }
    }

    public void initialInputDialog(String message) {
        alert_dialog = new $_InputDialog(context, message);

        alert_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
        alert_dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean not_empty_validation = new $_NotEmptyValidator(alert_dialog.getInput()).validate();
                if (not_empty_validation) {
                    String group_name = alert_dialog.getInput().getText().toString();
                    main_view_model.createGroup(group_name);
                } else {
                    $_Utils.makeToast(context, "Group name is required...", Toast.LENGTH_LONG);
                }
            }
        });


        alert_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });


        alert_dialog.show();
    }
}
