package com.example.whatsapp.Firebase;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.whatsapp.Activities.SignupActivity.SignupActivity;
import com.example.whatsapp.Utils.$Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class $FirebaseController {
    private FirebaseAuth firebase_auth;
    private FirebaseUser firebase_user;


    public $FirebaseController() {
        this.firebase_auth = FirebaseAuth.getInstance();
    }

    public Task  createAccountByGmail(String email, String password) {
        return this.firebase_auth.createUserWithEmailAndPassword(email, password);
    }


    // Getter and setter

    public FirebaseUser getFirebase_user() {
        return firebase_user;
    }

    public void setFirebase_user(FirebaseUser firebase_user) {
        this.firebase_user = firebase_user;
    }
}
