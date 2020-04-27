package com.example.whatsapp.ui.Activitys.FindFriendActivity.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.Utils.$_Utils;
import com.example.whatsapp.data.$_FirebaseData;
import com.example.whatsapp.model.$_ContactModel;
import com.example.whatsapp.ui.Activitys.ProfileActivity.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import xyz.schwaab.avvylib.AvatarView;

public class $_FindFriendAdapter {
    private FirebaseRecyclerOptions<$_ContactModel> options;
    private FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder> adapter;
    private Context context;

    public $_FindFriendAdapter(final Context context) {
        this.options = new FirebaseRecyclerOptions.Builder<$_ContactModel>()
                .setQuery($_FirebaseData.getINSTANCE().getRoot_database_reference().child("Users"), $_ContactModel.class)
                .build();

        this.adapter = new FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull $_FindFriendViewHolder holder, final int position, @NonNull $_ContactModel model) {
                holder.getUser_name().setText(model.getName());
                holder.getUser_status().setText(model.getStatus());
                try {
                    Picasso.get().load(model.getImage()).placeholder(R.drawable.default_image).into(holder.getUser_image());

                }catch (Exception ex){

                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user_id = getRef(position).getKey();
                        Bundle bundle = new Bundle();
                        bundle.putString("user id", user_id);
                        $_Utils.goToTargetActivityWithData(context, ProfileActivity.class, bundle);
                    }
                });

            }

            @NonNull
            @Override
            public $_FindFriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new $_FindFriendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
            }
        };
        this.context = context;
    }


    public static class $_FindFriendViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView user_name;
        private AppCompatTextView user_status;
        private AvatarView user_image;

        public $_FindFriendViewHolder(@NonNull View item_view) {
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

    public FirebaseRecyclerAdapter<$_ContactModel, $_FindFriendViewHolder> getAdapter() {
        return adapter;
    }
}
