package com.example.whatsapp.ui.Activitys.ProfileActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class $_ProfileActivityViewModel extends ViewModel {
    private MutableLiveData<$_UserModel> live_data_user_information;

    public $_ProfileActivityViewModel() {
        this.live_data_user_information = new MutableLiveData<>();
    }

    public void getUserInformation(final String user_id){
        $_FirebaseData.getINSTANCE().getUserInformation(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot data) {
                if(data.exists()){
                    String email = data.child("email").getValue(String.class);
                    String name = data.child("name").getValue(String.class);
                    String status = data.child("status").getValue(String.class);
                    String image = data.child("image").getValue(String.class);
                    $_UserModel user_model = new $_UserModel(user_id, email, name, status, image);
                    live_data_user_information.setValue(user_model);
                }else{
                    live_data_user_information.setValue(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError database_error) {

            }
        });
    }

    public MutableLiveData<$_UserModel> getLive_data_user_information() {
        return live_data_user_information;
    }
}
