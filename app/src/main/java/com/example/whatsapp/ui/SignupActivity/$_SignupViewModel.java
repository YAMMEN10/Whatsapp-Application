package com.example.whatsapp.ui.SignupActivity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class $_SignupViewModel extends ViewModel {
    private MutableLiveData<$_UserModel> live_data_user_model;

    public $_SignupViewModel() {
        this.live_data_user_model = new MutableLiveData<>();
    }

    public void createAccountByGmail(String email, String password) {
        $_FirebaseData.getINSTANCE().createAccountByGmail(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            String email = task.getResult().getUser().getEmail();
                            $_FirebaseData.getINSTANCE().storeUsers(uid);
                            $_UserModel user_model = new $_UserModel(uid, email);
                            live_data_user_model.setValue(user_model);
                        } else {
                            live_data_user_model.setValue(null);
                        }
                    }
                });
    }

    public MutableLiveData<$_UserModel> getLive_data_user_model() {
        return live_data_user_model;
    }
}
