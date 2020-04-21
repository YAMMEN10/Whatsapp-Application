package com.example.whatsapp.ui.Activitys.FindFriendActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class $_FindFriendViewModel extends ViewModel {
    private MutableLiveData<Boolean> live_data_set_adapter;

    public $_FindFriendViewModel() {
        this.live_data_set_adapter = new MutableLiveData<>();
    }


    public MutableLiveData<Boolean> getLive_data_set_adapter() {
        return live_data_set_adapter;
    }

    public void setLive_data_set_adapter(MutableLiveData<Boolean> live_data_set_adapter) {
        this.live_data_set_adapter = live_data_set_adapter;
    }
}
