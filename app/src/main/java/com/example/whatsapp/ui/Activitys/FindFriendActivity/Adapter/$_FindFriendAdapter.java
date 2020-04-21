package com.example.whatsapp.ui.Activitys.FindFriendActivity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import xyz.schwaab.avvylib.AvatarView;

public class $_FindFriendAdapter {
    private FirebaseRecyclerOptions<$_ContactModel> options;
    private FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder> adapter;

    public $_FindFriendAdapter() {
        this.options = new FirebaseRecyclerOptions.Builder<$_ContactModel>()
                .setQuery($_FirebaseData.getINSTANCE().getRoot_database_reference().child("Users"), $_ContactModel.class)
                .build();

        this.adapter = new FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull $_FindFriendViewHolder holder, int position, @NonNull $_ContactModel model) {
                holder.getFind_friend_name().setText(model.getName());
                holder.getFind_friend_status().setText(model.getStatus());
                try{
                    Picasso.get().load(model.getImage()).into(holder.getFind_friend_image());

                }catch (IllegalArgumentException ex){

                }

            }

            @NonNull
            @Override
            public $_FindFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new $_FindFriendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false));
            }
        };
    }




    public static class $_FindFriendViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView find_friend_name;
        private AppCompatTextView find_friend_status;
        private AvatarView find_friend_image;
        public $_FindFriendViewHolder(@NonNull View item_view) {
            super(item_view);
            this.find_friend_name = item_view.findViewById(R.id.find_friend_name);
            this.find_friend_status = item_view.findViewById(R.id.find_friend_status);
            this.find_friend_image = item_view.findViewById(R.id.find_friend_image);
        }

        public AppCompatTextView getFind_friend_name() {
            return find_friend_name;
        }

        public void setFind_friend_name(AppCompatTextView find_friend_name) {
            this.find_friend_name = find_friend_name;
        }

        public AppCompatTextView getFind_friend_status() {
            return find_friend_status;
        }

        public void setFind_friend_status(AppCompatTextView find_friend_status) {
            this.find_friend_status = find_friend_status;
        }

        public AvatarView getFind_friend_image() {
            return find_friend_image;
        }

        public void setFind_friend_image(AvatarView find_friend_image) {
            this.find_friend_image = find_friend_image;
        }
    }

    public FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder> getAdapter() {
        return adapter;
    }
}
