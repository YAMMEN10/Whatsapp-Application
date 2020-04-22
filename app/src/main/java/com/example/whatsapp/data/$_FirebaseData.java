package com.example.whatsapp.data;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Map;

public class $_FirebaseData {
    private FirebaseAuth firebase_auth;
    private FirebaseUser firebase_user;
    private DatabaseReference root_database_reference;
    private StorageReference user_profile_image_reference;
    private static $_FirebaseData INSTANCE;

    public $_FirebaseData() {
        this.firebase_auth = FirebaseAuth.getInstance();
        this.root_database_reference = FirebaseDatabase.getInstance().getReference();
        this.user_profile_image_reference = FirebaseStorage.getInstance().getReference().child("Profile Images's");
        this.firebase_user = null;
    }


    public Task<AuthResult> createAccountByGmail(String email, String password) {
        return this.firebase_auth.createUserWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signinAccountByGmail(String email, String password) {
        return this.firebase_auth.signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> signInWithPhoneAuthCredential(PhoneAuthCredential phone_auth_credential){
        return this.firebase_auth.signInWithCredential(phone_auth_credential);
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

    public DatabaseReference checkUsernameIsExist(String user_id) {
        return this.root_database_reference.child("Users").
                child(user_id);
    }

    public Task<Void> saveUserName(String username) {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid()).child("name").setValue(username);
    }

    public Task<Void> saveUserStatus(String status) {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid()).child("status").setValue(status);
    }

    public Task<Void> saveUserImage(String image) {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid()).child("image").setValue(image);
    }

    public Task<Uri> saveUserImageToStorage(Uri image){
        final StorageReference user_image_reference = this.user_profile_image_reference.child("Profile Images's").child(this.firebase_user.getUid() + ".jpg");

        return user_image_reference.putFile(image).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return user_image_reference.getDownloadUrl();
            }
        });




    }

    public DatabaseReference getSettingInformation() {
        return this.root_database_reference.child("Users").child(this.firebase_user.getUid());
    }

    public DatabaseReference checkGroupIsExist(String group_name){
        return this.root_database_reference.child("Groups").child(group_name);
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

    public DatabaseReference getMessageOfGroup(String group_name){
        return this.root_database_reference.child("Groups").child(group_name);
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

    public DatabaseReference getRoot_database_reference() {
        return root_database_reference;
    }

    public void setRoot_database_reference(DatabaseReference root_database_reference) {
        this.root_database_reference = root_database_reference;
    }
}
