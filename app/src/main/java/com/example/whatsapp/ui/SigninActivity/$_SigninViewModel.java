package com.example.whatsapp.ui.SigninActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class $_SigninViewModel extends ViewModel {
    private MutableLiveData<$_UserModel> live_data_user_model;

    public $_SigninViewModel() {
        this.live_data_user_model = new MutableLiveData<>();
    }

    public void signinAccountByGmail(String email, String password) {
        $_FirebaseData.getINSTANCE().signinAccountByGmail(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            String email = task.getResult().getUser().getEmail();
                            $_UserModel user_model = new $_UserModel(uid, "", email, "");
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
