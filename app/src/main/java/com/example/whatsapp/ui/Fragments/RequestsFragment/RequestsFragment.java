package com.example.whatsapp.ui.Fragments.RequestsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.databinding.FragmentRequestsBinding;
import com.example.whatsapp.ui.Fragments.RequestsFragment.Adapter.$_RequestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements $_InitializationView {
    private FragmentRequestsBinding fragment_requests_binding;
    private $_RequestAdapter request_adapter;
    RequestsFragment context;
    private $_RequestViewModel request_view_model;
    String current_user_id;
    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.fragment_requests_binding = FragmentRequestsBinding.inflate(inflater, container, false);
        View view = this.fragment_requests_binding.getRoot();
        this.initializationView();
        this.initializationActions();
        return view;
    }

    @Override
    public void initializationView() {
        this.context = this;
        $_FirebaseData.getINSTANCE().setFirebase_user($_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser());
        this.current_user_id = $_FirebaseData.getINSTANCE().getFirebase_auth().getCurrentUser().getUid();
        this.request_view_model = ViewModelProviders.of(this.context).get($_RequestViewModel.class);
        this.request_adapter = new $_RequestAdapter(this.context, this.current_user_id,this.request_view_model);
        this.fragment_requests_binding.recyclerViewRequests.setLayoutManager(new LinearLayoutManager(getContext()));
        this.fragment_requests_binding.recyclerViewRequests.setAdapter(this.request_adapter.getAdapter());
        this.request_adapter.getAdapter().startListening();
    }

    @Override
    public void initializationActions() {

    }
}
