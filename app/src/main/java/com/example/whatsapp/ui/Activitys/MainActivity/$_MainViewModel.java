package com.example.whatsapp.ui.Activitys.MainActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class $_MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> live_data_logout;
    private MutableLiveData<Pair<Boolean, String>> live_data_create_group;
    private MutableLiveData<Pair<Boolean, String>> live_data_check_username_is_exist;

    public $_MainViewModel() {
        this.live_data_logout = new MutableLiveData<>();
        this.live_data_create_group = new MutableLiveData<>();
        this.live_data_check_username_is_exist = new MutableLiveData<>();
    }

    public void signOutAccount() {
        boolean sign_out = $_FirebaseData.getINSTANCE().signOutAccount();
        this.live_data_logout.setValue(sign_out);
    }

    public void checkUsernameIsExist() {
        $_FirebaseData.getINSTANCE().checkUsernameIsExist($_FirebaseData.getINSTANCE().getFirebase_user().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data_snapshot) {
                if (data_snapshot.child("name").exists() && !data_snapshot.child("name").getValue(String.class).isEmpty()) {
                    live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(true, "Your welcome ..."));
                } else {
                    live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(false, "First, please fill your information_"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_error) {
                live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(false, database_error.getMessage()));
            }
        });
    }

    public void checkGroupIsExist(final String group_name) {
        $_FirebaseData.getINSTANCE().checkGroupIsExist(group_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if (!data.exists()) {
                    createGroup(group_name);
                } else {
                    live_data_create_group.setValue(new Pair<Boolean, String>(false, "Group " + group_name + " is exist"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError data_error) {
                live_data_create_group.setValue(new Pair<Boolean, String>(false, "Group " + group_name + " is exist"));

            }
        });
    }

    public void createGroup(final String group_name) {
        $_FirebaseData.getINSTANCE().createGroup(group_name).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_create_group.setValue(new Pair<Boolean, String>(true, "Group " + group_name + " created successfully"));

                } else {
                    live_data_create_group.setValue(new Pair<Boolean, String>(true, "Group " + group_name + " unsuccessfully created"));
                }
            }
        });
    }


    public MutableLiveData<Boolean> getLive_data_logout() {
        return live_data_logout;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_check_username_is_exist() {
        return live_data_check_username_is_exist;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_create_group() {
        return live_data_create_group;
    }
}
