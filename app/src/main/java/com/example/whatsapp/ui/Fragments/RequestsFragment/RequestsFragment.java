package com.example.whatsapp.ui.Fragments.RequestsFragment;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_InitializationView;
import com.example.whatsapp.databinding.FragmentRequestsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements $_InitializationView {
    private FragmentRequestsBinding fragment_requests_binding;

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.fragment_requests_binding = FragmentRequestsBinding.inflate(inflater, container,false);
        View view = this.fragment_requests_binding.getRoot();


        return view;
    }

    @Override
    public void initializationView() {
   }

    @Override
    public void initializationActions() {

    }
}
