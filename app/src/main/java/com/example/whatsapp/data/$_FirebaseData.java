package com.example.whatsapp.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

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

    public Task<Void> storeUsers(Map user_model) {
        return this.root_database_reference.child("Users").child(user_model.get("id").toString()).setValue(user_model);
    }

    public boolean signOutAccount() {
        try {
            this.firebase_auth.signOut();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public DatabaseReference checkUsernameIsExist() {
        String user_id = this.firebase_user.getUid();
        return this.root_database_reference.child("Users").
                child(user_id);
    }

    public Task<Void> saveUserNameInformation(String username) {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid()).child("name").setValue(username);
    }

    public Task<Void> saveUserStatusInformation(String status) {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid()).child("status").setValue(status);
    }

    public DatabaseReference getSettingInformation() {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid());
    }

    public Task<Void> createGroup(String group_name) {
        return this.root_database_reference.child("Groups").child(group_name).setValue("");
    }


    public DatabaseReference getAllGroups() {
        try {
            DatabaseReference data_base_reference = this.root_database_reference.child("Groups");
            return data_base_reference;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public DatabaseReference getCurrentUsername() {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid());
    }

    public DatabaseReference saveMessageGroup(String group_name) {
        String message_key = $_FirebaseData.getINSTANCE().root_database_reference.child("Groups").child(group_name).push().getKey();
        return $_FirebaseData.getINSTANCE().root_database_reference.child("Groups").child(group_name).child(message_key);
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
