package com.example.whatsapp.ui.MainActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> live_data_logout;

    public MainViewModel() {
        this.live_data_logout = new MutableLiveData<>();
    }

    public void signOutAccount() {
        boolean sign_out = $_FirebaseData.getINSTANCE().signOutAccount();
        this.live_data_logout.setValue(sign_out);
    }

    public MutableLiveData<Boolean> getLive_data_logout() {
        return live_data_logout;
    }
}
