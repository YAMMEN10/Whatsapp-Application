package com.example.whatsapp.ui.Fragments.RequestsFragment.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.example.whatsapp.model.$_ContactsKey;
import com.example.whatsapp.ui.Activitys.ProfileActivity.ProfileActivity;
import com.example.whatsapp.ui.Fragments.RequestsFragment.$_RequestViewModel;
import com.example.whatsapp.ui.Fragments.RequestsFragment.RequestsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import xyz.schwaab.avvylib.AvatarView;

public class $_RequestAdapter {
    private FirebaseRecyclerOptions<$_ContactsKey> options;
    private FirebaseRecyclerAdapter<$_ContactsKey, $_RequestAdapter.$_RequestViewHolder> adapter;

    public $_RequestAdapter(final RequestsFragment context, String current_user_id,final $_RequestViewModel request_view_model) {
        this.options = new FirebaseRecyclerOptions.Builder<$_ContactsKey>()
                .setQuery($_FirebaseData.getINSTANCE().getRoot_database_reference().child("Messages Requests").child(current_user_id), $_ContactsKey.class)
                .build();

        this.adapter = new FirebaseRecyclerAdapter<$_ContactsKey, $_RequestAdapter.$_RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final $_RequestAdapter.$_RequestViewHolder holder, final int position, @NonNull final $_ContactsKey model) {

                String key = model.getKey();
                request_view_model.getUserInformation(key);
                request_view_model.getLive_data_user_information().observe(context, new Observer<$_ContactModel>() {
                    @Override
                    public void onChanged($_ContactModel contact_model) {
                        holder.getRequest_name().setText(contact_model.getName());
                        holder.getRequest_status().setText(contact_model.getStatus());
                        try {
                            Picasso.get().load(contact_model.getImage()).placeholder(R.drawable.default_image).into(holder.getRequest_image());

                        }catch (Exception ex){

                        }
                    }
                });
            }

            @NonNull
            @Override
            public $_RequestAdapter.$_RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new $_RequestAdapter.$_RequestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
            }
        };
    }


    public static class $_RequestViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView request_name;
        private AppCompatTextView request_status;
        private AvatarView request_image;


        public $_RequestViewHolder(@NonNull View item_view) {
            super(item_view);
            this.request_name = item_view.findViewById(R.id.request_name);
            this.request_status = item_view.findViewById(R.id.request_status);
            this.request_image = item_view.findViewById(R.id.request_image);
        }

        public AppCompatTextView getRequest_name() {
            return request_name;
        }

        public void setRequest_name(AppCompatTextView request_name) {
            this.request_name = request_name;
        }

        public AppCompatTextView getRequest_status() {
            return request_status;
        }

        public void setRequest_status(AppCompatTextView request_status) {
            this.request_status = request_status;
        }

        public AvatarView getRequest_image() {
            return request_image;
        }

        public void setRequest_image(AvatarView request_image) {
            this.request_image = request_image;
        }
    }

    public FirebaseRecyclerAdapter<$_ContactsKey, $_RequestAdapter.$_RequestViewHolder> getAdapter() {
        return adapter;
    }
}