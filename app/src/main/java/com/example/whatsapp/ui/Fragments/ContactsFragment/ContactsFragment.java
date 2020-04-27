package com.example.whatsapp.ui.Fragments.ContactsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.FragmentContactsBinding;
import com.example.whatsapp.databinding.FragmentGroupsBinding;
import com.example.whatsapp.ui.Fragments.ContactsFragment.Adapter.$_ContactsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements $_InitializationView {
    private FragmentContactsBinding fragment_contacts_binding;
    private $_ContactsAdapter contacts_adapter;
    private String current_user_id;
    private $_ContactsFragmentViewModel contacts_fragment_view_model;
    private ContactsFragment context;
    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        this.fragment_contacts_binding = FragmentContactsBinding.inflate(inflater, container, false);
        View view = this.fragment_contacts_binding.getRoot();
        this.initializationView();
        this.initializationActions();

        return view;
    }

    @Override
    public void initializationView() {
        this.context = ContactsFragment.this;
        this.current_user_id = $_FirebaseData.getINSTANCE().getFirebase_user().getUid();
        this.contacts_fragment_view_model = ViewModelProviders.of(this.context).get($_ContactsFragmentViewModel.class);

        this.contacts_adapter = new $_ContactsAdapter(this,this.current_user_id, this.contacts_fragment_view_model);

        this.fragment_contacts_binding.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        this.fragment_contacts_binding.recyclerViewContacts.setAdapter(this.contacts_adapter.getAdapter());
        this.contacts_adapter.getAdapter().startListening();

    }

    @Override
    public void initializationActions() {

    }
}
