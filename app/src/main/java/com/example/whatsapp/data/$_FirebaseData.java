package com.example.whatsapp.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class $_FirebaseData {
    private FirebaseAuth firebase_auth;
    private FirebaseUser firebase_user;
    private DatabaseReference root_database_reference;
    private static $_FirebaseData INSTANCE;

    public $_FirebaseData() {
        this.firebase_auth = FirebaseAuth.getInstance();
        this.root_database_reference = FirebaseDatabase.getInstance().getReference();
        this.firebase_user = null;
    }


    public Task<AuthResult> createAccountByGmail(String email, String password) {
        return this.firebase_auth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signinAccountByGmail(String email, String password) {
        return this.firebase_auth.signInWithEmailAndPassword(email, password);
    }

    public Task storeUsers(String uid){
        return this.root_database_reference.child("Users").child(uid).setValue("");
    }

    public boolean signOutAccount() {
        try {
            this.firebase_auth.signOut();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    // Getter and Setter
    public static $_FirebaseData getINSTANCE() {
        if (INSTANCE == null) {
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
