package com.example.whatsapp.ui.Fragments.RequestsFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class $_RequestViewModel extends ViewModel {
    private MutableLiveData<$_ContactModel> live_data_user_information;
        private MutableLiveData<Boolean> live_data_remove_message_request;

    public $_RequestViewModel() {
        this.live_data_user_information = new MutableLiveData<>();
        this.live_data_remove_message_request = new MutableLiveData<>();
    }

    public void getUserInformation(final String user_id) {
        $_FirebaseData.getINSTANCE().getUserInformation(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if (data.exists()) {
                    String name = data.child("name").getValue(String.class);
                    String status = data.child("status").getValue(String.class);
                    String image = data.child("image").getValue(String.class);
                    $_ContactModel user_model = new $_ContactModel(name, status, image);
                    live_data_user_information.setValue(user_model);
                } else {
                    live_data_user_information.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_error) {

            }
        });
    }

    public void removeSendChatRequest(final String sender_user_id, final String receiver_user_id) {
        $_FirebaseData.getINSTANCE().removeSendChatRequest(receiver_user_id, sender_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    removeReceiveChatRequest(receiver_user_id, sender_user_id);
                } else {

                }
            }
        });
    }

    public void removeReceiveChatRequest(String receiver_user_id, String sender_user_id) {
        $_FirebaseData.getINSTANCE().removeReceiveChatRequest(sender_user_id, receiver_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_remove_message_request.setValue(true);
                } else {
                    live_data_remove_message_request.setValue(false);
                }
            }
        });
    }

    public void acceptMessageRequestFromSender(final String sender_user_id, final String receiver_user_id){
        $_FirebaseData.getINSTANCE().acceptMessageRequestFromSender(sender_user_id, receiver_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    acceptMessageRequestFromReceiver(receiver_user_id, sender_user_id);
                }else{

                }
            }
        });
    }

    public void acceptMessageRequestFromReceiver(final String receiver_user_id, final String sender_user_id){
        $_FirebaseData.getINSTANCE().acceptMessageRequestFromReceiver(receiver_user_id, sender_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    removeSendChatRequest(sender_user_id, receiver_user_id);
                }else{

                }
            }
        });
    }


    public MutableLiveData<$_ContactModel> getLive_data_user_information() {
        return live_data_user_information;
    }
}
