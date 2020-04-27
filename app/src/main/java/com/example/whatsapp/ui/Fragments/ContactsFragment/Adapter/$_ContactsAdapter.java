package com.example.whatsapp.ui.Fragments.ContactsFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.example.whatsapp.model.$_ContactsKey;
import com.example.whatsapp.ui.Fragments.ContactsFragment.$_ContactsFragmentViewModel;
import com.example.whatsapp.ui.Fragments.ContactsFragment.ContactsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import xyz.schwaab.avvylib.AvatarView;

public class $_ContactsAdapter {
    private FirebaseRecyclerOptions<$_ContactsKey> options;
    private FirebaseRecyclerAdapter<$_ContactsKey, $_ContactsAdapter.$_ContactsViewHolder> adapter;
    private String current_user_id;

    public $_ContactsAdapter(final ContactsFragment context, String current_user_id, final $_ContactsFragmentViewModel contacts_fragment_view_model) {

        this.current_user_id = current_user_id;
        this.options = new FirebaseRecyclerOptions.Builder<$_ContactsKey>()
                .setQuery($_FirebaseData.getINSTANCE().getRoot_database_reference().child("Contacts").child(this.current_user_id), $_ContactsKey.class)
                .build();

        this.adapter = new FirebaseRecyclerAdapter<$_ContactsKey, $_ContactsViewHolder>(this.options) {
            @Override
            protected void onBindViewHolder(@NonNull final $_ContactsViewHolder holder, int position, @NonNull $_ContactsKey model) {

//                String user_key = getRef(position).getKey();
                String user_key = model.getKey();
                contacts_fragment_view_model.getUserInformation(user_key);
                contacts_fragment_view_model.getLive_data_user_information().observe(context, new Observer<$_ContactModel>() {
                    @Override
                    public void onChanged($_ContactModel contact_model) {
                        holder.getUser_name().setText(contact_model.getName());
                        holder.getUser_status().setText(contact_model.getStatus());
                        try {
                            Picasso.get().load(contact_model.getImage()).placeholder(R.drawable.default_image).into(holder.user_image);
                        } catch (Exception ex) {

                        }
                    }
                });

            }

            @NonNull
            @Override
            public $_ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new $_ContactsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
            }
        };

    }

    public FirebaseRecyclerAdapter<$_ContactsKey, $_ContactsViewHolder> getAdapter() {
        return adapter;
    }

    public class $_ContactsViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView user_name;
        private AppCompatTextView user_status;
        private AvatarView user_image;

        public $_ContactsViewHolder(@NonNull View item_view) {
            super(item_view);
            this.user_name = item_view.findViewById(R.id.user_name);
            this.user_status = item_view.findViewById(R.id.user_status);
            this.user_image = item_view.findViewById(R.id.user_image);
        }

        public AppCompatTextView getUser_name() {
            return user_name;
        }

        public void setUser_name(AppCompatTextView user_name) {
            this.user_name = user_name;
        }

        public AppCompatTextView getUser_status() {
            return user_status;
        }

        public void setUser_status(AppCompatTextView user_status) {
            this.user_status = user_status;
        }

        public AvatarView getUser_image() {
            return user_image;
        }

        public void setUser_image(AvatarView user_image) {
            this.user_image = user_image;
        }
    }
}
