package com.example.whatsapp.ui.SettingActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class $_SettingViewModel extends ViewModel {
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_information;
    private MutableLiveData<$_UserModel> live_data_get_user_model;

    public $_SettingViewModel() {
        this.live_data_save_user_information = new MutableLiveData<>();
        this.live_data_get_user_model = new MutableLiveData<>();
    }

    public void saveUserNameInformation(String username) {
        $_FirebaseData.getINSTANCE().saveUserNameInformation(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(true, "Successful information updated"));
                } else {
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(false, "Error in update information"));
                }
            }
        });
    }

    public void saveUserStatusInformation(String status) {
        $_FirebaseData.getINSTANCE().saveUserStatusInformation(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(true, "Successful information updated"));
                } else {
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(false, "Error in update information"));
                }
            }
        });
    }


    public void getSettingInformation() {
        $_FirebaseData.getINSTANCE().getSettingInformation().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data_snapshot) {
                if (data_snapshot.exists()) {
                    String id = data_snapshot.child("id").getValue(String.class);
                    String email = data_snapshot.child("email").getValue(String.class);
                    String name = data_snapshot.child("name").getValue(String.class);
                    String status = data_snapshot.child("status").getValue(String.class);
                    String picture = data_snapshot.child("picture").getValue(String.class);
                    $_UserModel user_model = new $_UserModel(id, email, name, status, picture);
                    live_data_get_user_model.setValue(user_model);
                }else{
                    live_data_get_user_model.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                live_data_get_user_model.setValue(null);
            }
        });
    }


    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_information() {
        return live_data_save_user_information;
    }

    public MutableLiveData<$_UserModel> getLive_data_get_user_model() {
        return live_data_get_user_model;
    }
}
