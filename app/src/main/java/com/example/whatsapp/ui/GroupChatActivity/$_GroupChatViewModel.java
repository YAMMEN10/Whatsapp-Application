package com.example.whatsapp.ui.GroupChatActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.whatsapp.data.$_FirebaseData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class $_GroupChatViewModel extends ViewModel {
    private MutableLiveData<String> live_data_username;

    public $_GroupChatViewModel() {
        this.live_data_username = new MutableLiveData<>();
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

    public MutableLiveData<String> getLive_data_username() {
        return live_data_username;
    }
}
