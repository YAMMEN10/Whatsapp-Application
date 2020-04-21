package com.example.whatsapp.ui.Activitys.SigninPhoneNumberActivity;

import android.app.Activity;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SigninPhoneNumberViewModel extends ViewModel {
    private MutableLiveData<Pair<Integer, String>> live_data_signin_phone_number;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String phone_number;


    public SigninPhoneNumberViewModel() {
        this.live_data_signin_phone_number = new MutableLiveData<>();
        this.callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(final PhoneAuthCredential phone_auth_credential) {
                $_FirebaseData.getINSTANCE().signInWithPhoneAuthCredential(phone_auth_credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            $_UserModel user_model = new $_UserModel(task.getResult().getUser().getUid(), phone_number, "", "", "");
//
//                            $_FirebaseData.getINSTANCE().storeUsers(user_model.map());

                            live_data_signin_phone_number.setValue(new Pair<Integer, String>(1, "Congratulations, you're logged in Successfully"));
                        } else {
                            live_data_signin_phone_number.setValue(new Pair<Integer, String>(-1, "Error " + task.getException().getMessage()));
                        }
                    }
                });
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                live_data_signin_phone_number.setValue(new Pair<Integer, String>(3, "Invalid phone number, please reinter your phone number"));
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                live_data_signin_phone_number.setValue(new Pair<Integer, String>(2, "Verification code has been sent, please check and verify..."));

            }
        };
    }

    public void signinWithPhoneNumber(Activity context, String phone_number) {
        this.phone_number = phone_number;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone_number,
                60,
                TimeUnit.SECONDS,
                context,
                callbacks
        );
    }


    public MutableLiveData<Pair<Integer, String>> getLive_data_signin_phone_number() {
        return live_data_signin_phone_number;
    }

    public String getmVerificationId() {
        return mVerificationId;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
