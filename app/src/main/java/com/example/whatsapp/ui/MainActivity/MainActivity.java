package com.example.whatsapp.ui.MainActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ActivityMainBinding;
import com.example.whatsapp.databinding.AppbarLayoutBinding;
import com.example.whatsapp.ui.MainActivity.Adapter.$_TabsAccessAdapter;
import com.example.whatsapp.ui.SigninActivity.SigninActivity;

public class MainActivity extends AppCompatActivity implements $_InitializationView {
    private AppbarLayoutBinding appbar_layout_binding;
    private ActivityMainBinding activity_main_binding;
    private $_TabsAccessAdapter tabs_access_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initializationView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if ($_FirebaseData.getINSTANCE().getFirebase_user() == null) {
            $_Utils.go_to_target_activity(MainActivity.this, SigninActivity.class);
        }
    }

    @Override
    public void initializationView() {
        this.activity_main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.activity_main_binding.getRoot();
        setContentView(view);
        this.appbar_layout_binding = activity_main_binding.mainPageToolbar;

        setSupportActionBar(this.appbar_layout_binding.mainAppbar);
        getSupportActionBar().setTitle("Whatsapp");
        this.tabs_access_adapter = new $_TabsAccessAdapter(getSupportFragmentManager(), 3);
        activity_main_binding.mainTabsViewPage.setAdapter(this.tabs_access_adapter);
        activity_main_binding.mainTapsLayout.setupWithViewPager(activity_main_binding.mainTabsViewPage);
    }

    @Override
    public void initializationActions() {

    }
}
