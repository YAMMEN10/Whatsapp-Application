package com.example.whatsapp.ui.SettingActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SettingViewModel extends ViewModel {
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_information;

    public SettingViewModel() {
        this.live_data_save_user_information = new MutableLiveData<>();
    }

    public void saveUserInformation(String username, String status){
        $_FirebaseData.getINSTANCE().saveUserInformation(username, status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(true, "Successful information updated"));
                }else{
                    live_data_save_user_information.setValue(new Pair<Boolean, String>(false, "Error in update information"));
                }
            }
        });
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_information() {
        return live_data_save_user_information;
    }
}
