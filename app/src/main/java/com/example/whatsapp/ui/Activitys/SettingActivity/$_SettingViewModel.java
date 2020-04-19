package com.example.whatsapp.ui.Activitys.SettingActivity;

import android.net.Uri;
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
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_name;
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_status;
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_image;
    private MutableLiveData<$_UserModel> live_data_get_user_model;
    private MutableLiveData<Pair<Boolean, String>> live_data_save_user_image_to_storage;

    public $_SettingViewModel() {
        this.live_data_save_user_name = new MutableLiveData<>();
        this.live_data_save_user_status = new MutableLiveData<>();
        this.live_data_save_user_image = new MutableLiveData<>();
        this.live_data_get_user_model = new MutableLiveData<>();
        this.live_data_save_user_image_to_storage = new MutableLiveData<>();
    }

    public void saveUserName(String username) {
        $_FirebaseData.getINSTANCE().saveUserName(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_name.setValue(new Pair<Boolean, String>(true, "Successful name updated"));
                } else {
                    live_data_save_user_name.setValue(new Pair<Boolean, String>(false, "Error in update name"));
                }
            }
        });
    }

    public void saveUserStatus(String status) {
        $_FirebaseData.getINSTANCE().saveUserStatus(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_status.setValue(new Pair<Boolean, String>(true, "Successful status updated"));
                } else {
                    live_data_save_user_status.setValue(new Pair<Boolean, String>(false, "Error in update status"));
                }
            }
        });
    }

    public void saveUserImage(String image) {
        $_FirebaseData.getINSTANCE().saveUserImage(image).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_image.setValue(new Pair<Boolean, String>(true, "Successful image updated"));
                } else {
                    live_data_save_user_image.setValue(new Pair<Boolean, String>(false, "Error in update image"));
                }
            }
        });
    }

    public void saveUserImageToStorage(Uri image) {
        $_FirebaseData.getINSTANCE().saveUserImageToStorage(image).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    live_data_save_user_image_to_storage.setValue(new Pair<Boolean, String>(true, task.getResult().toString()));
                } else {
                    live_data_save_user_image_to_storage.setValue(new Pair<Boolean, String>(false, task.getException().getMessage()));
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
                    String picture = data_snapshot.child("image").getValue(String.class);
                    $_UserModel user_model = new $_UserModel(id, email, name, status, picture);
                    live_data_get_user_model.setValue(user_model);
                } else {
                    live_data_get_user_model.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                live_data_get_user_model.setValue(null);
            }
        });
    }


    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_name() {
        return live_data_save_user_name;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_status() {
        return live_data_save_user_status;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_image() {
        return live_data_save_user_image;
    }

    public MutableLiveData<$_UserModel> getLive_data_get_user_model() {
        return live_data_get_user_model;
    }

    public MutableLiveData<Pair<Boolean, String>> getLive_data_save_user_image_to_storage() {
        return live_data_save_user_image_to_storage;
    }
}
