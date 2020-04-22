package com.example.whatsapp.ui.Activitys.VerificationCodeActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class VerificationCodeViewModel extends ViewModel {
    private MutableLiveData<Pair<Integer, String>> live_data_verification_code;

    public VerificationCodeViewModel() {
        this.live_data_verification_code = new MutableLiveData<>();

    }

    public void verificationCode(String code, final String my_verification_id, final String phone_number) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(my_verification_id, code);
        $_FirebaseData.getINSTANCE().signInWithPhoneAuthCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    $_FirebaseData.getINSTANCE().checkUsernameIsExist(task.getResult().getUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot data_snapshot) {
                            if (!data_snapshot.exists()) {
                                String uid = task.getResult().getUser().getUid();
                                $_UserModel user_model = new $_UserModel(uid, phone_number, "", "", "");
                                $_FirebaseData.getINSTANCE().storeUsers(user_model.map());
                                live_data_verification_code.setValue(new Pair<Integer, String>(1, "Congratulations, you're logged in Successfully"));

                            } else {
                                live_data_verification_code.setValue(new Pair<Integer, String>(1, "Congratulations, you're logged in Successfully"));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError database_error) {
                            live_data_verification_code.setValue(new Pair<Integer, String>(-1, "Error : " + database_error.getMessage()));
                        }
                    });


                } else {
                    live_data_verification_code.setValue(new Pair<Integer, String>(-1, "Error : " + task.getException().getMessage()));
                }
            }
        });
    }


    public MutableLiveData<Pair<Integer, String>> getLive_data_verification_code() {
        return live_data_verification_code;
    }

}
