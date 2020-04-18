package com.example.whatsapp.ui.Fragments.GroupsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.ChatsItemBinding;
import com.example.whatsapp.databinding.FragmentGroupsBinding;
import com.example.whatsapp.model.$_GroupInformation;
import com.example.whatsapp.ui.Activitys.GroupChatActivity.GroupChatActivity;
import com.example.whatsapp.ui.Fragments.GroupsFragment.Adapter.$_GroupsAdapter;

import java.util.ArrayList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment implements $_InitializationView {

    private FragmentGroupsBinding fragment_group_binding;
    private ChatsItemBinding chats_item_binding;
    private $_GroupViewModel group_view_model;
    private GroupsFragment context;
    private ArrayList<$_GroupInformation> items;
    private $_GroupsAdapter group_adapter;

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.fragment_group_binding = FragmentGroupsBinding.inflate(inflater, container, false);
        View view = this.fragment_group_binding.getRoot();
        initializationView();
        initializationActions();

        return view;
    }


    @Override
    public void initializationView() {
        this.context = GroupsFragment.this;
        group_view_model = ViewModelProviders.of(this.context).get($_GroupViewModel.class);

        $_FirebaseData.getINSTANCE().setFirebase_user($_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser());

        this.items = new ArrayList<>();
        this.group_adapter = new $_GroupsAdapter(this.items);
        this.fragment_group_binding.recyclerViewGroup.setAdapter(this.group_adapter);
        this.fragment_group_binding.recyclerViewGroup.setLayoutManager(new LinearLayoutManager(getContext()));
        this.group_adapter.setOnItemClickListener(onItemClickListener);

        this.group_view_model.getAllGroups();
    }

    @Override
    public void initializationActions() {
        this.group_view_model.getLive_data_groups_set().observe(context, new Observer<Set<$_GroupInformation>>() {
            @Override
            public void onChanged(Set<$_GroupInformation> group_information) {
                if (group_information != null) {
                    items.clear();
                    items.addAll(group_information);
                    group_adapter.notifyDataSetChanged();
                }
            }
        });


    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder view_holder = (RecyclerView.ViewHolder) view.getTag();
            int position = view_holder.getAdapterPosition();
            $_GroupInformation specific_item = items.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("group_name", specific_item.getName());

            $_Utils.goToTargetActivityWithData(getContext(), GroupChatActivity.class, bundle);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.fragment_group_binding = null;
    }
}
