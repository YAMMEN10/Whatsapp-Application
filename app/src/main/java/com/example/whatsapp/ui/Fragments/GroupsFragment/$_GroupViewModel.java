package com.example.whatsapp.ui.Fragments.GroupsFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_GroupInformationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class $_GroupViewModel extends ViewModel {
    private MutableLiveData<Set<$_GroupInformationModel>> live_data_groups_set;

    public $_GroupViewModel() {
        this.live_data_groups_set = new MutableLiveData<>();
    }

    public void getAllGroups(){
        try {
            $_FirebaseData.getINSTANCE().getAllGroups().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot data) {
                    Iterator iterator = data.getChildren().iterator();
                    Set<$_GroupInformationModel> set = new HashSet<$_GroupInformationModel>();
                    while ((iterator.hasNext())){
                        $_GroupInformationModel group_information = new $_GroupInformationModel(((DataSnapshot)iterator.next()).getKey());
                        set.add(group_information);
                    }
                    live_data_groups_set.setValue(set);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (NullPointerException ex){
            // Do anythings
        }
    }

    public MutableLiveData<Set<$_GroupInformationModel>> getLive_data_groups_set() {
        return live_data_groups_set;
    }
}
