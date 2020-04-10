package com.example.whatsapp.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class $_FirebaseData {
    private  FirebaseAuth firebase_auth;
    private FirebaseUser firebase_user;
    private static $_FirebaseData INSTANCE;

    public $_FirebaseData() {
        firebase_auth = FirebaseAuth.getInstance();
    }


    public Task<AuthResult> createAccountByGmail(String email, String password) {
        return firebase_auth.createUserWithEmailAndPassword(email, password);
    }


    public static $_FirebaseData getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new $_FirebaseData();
        }
        return INSTANCE;
    }

    public FirebaseAuth getFirebase_auth() {
        return firebase_auth;
    }

    public void setFirebase_auth(FirebaseAuth firebase_auth) {
        this.firebase_auth = firebase_auth;
    }

    public FirebaseUser getFirebase_user() {
        return firebase_user;
    }

    public void setFirebase_user(FirebaseUser firebase_user) {
        this.firebase_user = firebase_user;
    }

    public static void setINSTANCE($_FirebaseData INSTANCE) {
        $_FirebaseData.INSTANCE = INSTANCE;
    }
}
