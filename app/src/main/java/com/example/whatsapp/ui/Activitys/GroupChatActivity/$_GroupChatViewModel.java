package com.example.whatsapp.ui.Activitys.GroupChatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_MessageModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class $_GroupChatViewModel extends ViewModel {
    private MutableLiveData<String> live_data_username;
    private MutableLiveData<$_MessageModel> live_data_messages_of_group;

    public $_GroupChatViewModel() {
        this.live_data_username = new MutableLiveData<>();
        this.live_data_messages_of_group = new MutableLiveData<>();
    }

    public void getCurrentUsername(){
        $_FirebaseData.getINSTANCE().getCurrentUsername().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if(data.exists()){
                    live_data_username.setValue(data.child("name").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError data_error) {
                live_data_username.setValue(null);

            }
        });
    }

    public void saveMessageGroup(String group_name, Map message_model){
        $_FirebaseData.getINSTANCE().saveMessageGroup(group_name).updateChildren(message_model);
    }

    public void getMessageOfGroup(String group_name){

        $_FirebaseData.getINSTANCE().getMessageOfGroup(group_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot data, @Nullable String s) {
                if(data.exists()){
                    live_data_messages_of_group.setValue(new $_MessageModel((HashMap) data.getValue()));
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot data, @Nullable String s) {
                if(data.exists()){

                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot data) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot data, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public MutableLiveData<String> getLive_data_username() {
        return live_data_username;
    }

    public MutableLiveData<$_MessageModel> getLive_data_messages_of_group() {
        return live_data_messages_of_group;
    }
}
