package com.example.whatsapp.ui.MainActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> live_data_logout;
    private MutableLiveData<Pair<Boolean, String>> live_data_check_username_is_exist;

    public MainViewModel() {
        this.live_data_logout = new MutableLiveData<>();
        this.live_data_check_username_is_exist = new MutableLiveData<>();
    }

    public void signOutAccount() {
        boolean sign_out = $_FirebaseData.getINSTANCE().signOutAccount();
        this.live_data_logout.setValue(sign_out);
    }

    public void checkUsernameIsExist(){
        $_FirebaseData.getINSTANCE().checkUsernameIsExist().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data_snapshot) {
                if(data_snapshot.child("name").exists()){
                    live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(true, "Your welcome ..."));
                }
                else{
                    live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(false, "First, please fill your information"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_error) {
                live_data_check_username_is_exist.setValue(new Pair<Boolean, String>(false, database_error.getMessage()));
            }
        });
    }

    public MutableLiveData<Boolean> getLive_data_logout() {
        return live_data_logout;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_check_username_is_exist() {
        return live_data_check_username_is_exist;
    }
}
