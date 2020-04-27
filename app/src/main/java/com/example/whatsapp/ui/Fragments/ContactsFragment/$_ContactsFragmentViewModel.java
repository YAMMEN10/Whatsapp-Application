package com.example.whatsapp.ui.Fragments.ContactsFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class $_ContactsFragmentViewModel extends ViewModel {
    private MutableLiveData<$_ContactModel> live_data_user_information;

    public $_ContactsFragmentViewModel() {
        this.live_data_user_information = new MutableLiveData<>();
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

    public MutableLiveData<$_ContactModel> getLive_data_user_information() {
        return live_data_user_information;
    }
}
