package com.example.whatsapp.ui.Activitys.ProfileActivity;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class $_ProfileActivityViewModel extends ViewModel {
    private MutableLiveData<$_UserModel> live_data_user_information;
    private MutableLiveData<Boolean> live_data_send_message_request;
    private MutableLiveData<Boolean> live_data_remove_message_request;
    private MutableLiveData<String> live_data_state_message_request;
    private MutableLiveData<Boolean> live_data_remove_friend;

    public $_ProfileActivityViewModel() {
        this.live_data_user_information = new MutableLiveData<>();
        this.live_data_send_message_request = new MutableLiveData<>();
        this.live_data_state_message_request = new MutableLiveData<>();
        this.live_data_remove_message_request = new MutableLiveData<>();
        this.live_data_remove_friend = new MutableLiveData<>();
    }

    public void getUserInformation(final String user_id) {
        $_FirebaseData.getINSTANCE().getUserInformation(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if (data.exists()) {
                    String email = data.child("email").getValue(String.class);
                    String name = data.child("name").getValue(String.class);
                    String status = data.child("status").getValue(String.class);
                    String image = data.child("image").getValue(String.class);
                    $_UserModel user_model = new $_UserModel(user_id, email, name, status, image);
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

    public void sendChatRequest(final String sender_user_id, final String receiver_user_id) {
        $_FirebaseData.getINSTANCE().sendChatRequest(sender_user_id, receiver_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    receiveChatRequest(receiver_user_id, sender_user_id);
                } else {

                }
            }
        });
    }

    public void receiveChatRequest(String receiver_user_id, String sender_user_id) {
        $_FirebaseData.getINSTANCE().receiveChatRequest(receiver_user_id, sender_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    live_data_send_message_request.setValue(true);
                } else {
                    live_data_send_message_request.setValue(false);

                }
            }
        });
    }

    public void getMessageChatStateFromSenderToReceiver(final String sender_user_id, final String receiver_user_id) {
        $_FirebaseData.getINSTANCE().getMessageChatStateFromSenderToReceiver(sender_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if (data.hasChild(receiver_user_id)) {
                    String state = data.child(receiver_user_id).child("request_type").getValue(String.class);
                    live_data_state_message_request.setValue(state);
                }else{
                    checkUserIsFriend(sender_user_id, receiver_user_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_error) {
                live_data_state_message_request.setValue(null);

            }
        });
    }

    public void checkUserIsFriend(String sender_user_id, final String receiver_user_id){
        $_FirebaseData.getINSTANCE().checkUserIsFriend(sender_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if(data.hasChild(receiver_user_id)){
                    live_data_state_message_request.setValue("friend");
                }else{
                    live_data_state_message_request.setValue("new");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_Eror) {

            }
        });

    }

    public void removeSendChatRequest(final String sender_user_id, final String receiver_user_id) {
        $_FirebaseData.getINSTANCE().removeSendChatRequest(sender_user_id, receiver_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        $_FirebaseData.getINSTANCE().removeReceiveChatRequest(receiver_user_id, sender_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void removeFriendFromSender(final String sender_user_id, final String receiver_user_id){
        $_FirebaseData.getINSTANCE().removeFriendFromSender(sender_user_id, receiver_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    removeFriendFromReceiver(receiver_user_id, sender_user_id);
                }else{

                }
            }
        });
    }

    public void removeFriendFromReceiver(final String receiver_user_id, final String sender_user_id){
        $_FirebaseData.getINSTANCE().removeFriendFromReceiver(receiver_user_id, sender_user_id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    live_data_remove_friend.setValue(true);
                }else{
                    live_data_remove_friend.setValue(true);
                }
            }
        });
    }



    public MutableLiveData<$_UserModel> getLive_data_user_information() {
        return live_data_user_information;
    }

    public MutableLiveData<Boolean> getLive_data_send_message_request() {
        return live_data_send_message_request;
    }

    public MutableLiveData<String> getLive_data_state_message_request() {
        return live_data_state_message_request;
    }

    public MutableLiveData<Boolean> getLive_data_remove_message_request() {
        return live_data_remove_message_request;
    }

    public MutableLiveData<Boolean> getLive_data_remove_friend() {
        return live_data_remove_friend;
    }
}
