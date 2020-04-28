package com.example.whatsapp.ui.Fragments.GroupsFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.model.$_GroupInformationModel;

import java.util.ArrayList;

public class $_GroupsAdapter extends RecyclerView.Adapter<$_GroupsAdapter.$_GroupViewHolder> {
    private ArrayList<$_GroupInformationModel> items;
    private View.OnClickListener mOnItemClickListener;

    public $_GroupsAdapter(ArrayList<$_GroupInformationModel> items) {
        this.items = items;

    }


    @NonNull
    @Override
    public $_GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new $_GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chats_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull $_GroupViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }



    class $_GroupViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView name;

        public $_GroupViewHolder(@NonNull final View item_view) {
            super(item_view);
            this.name = item_view.findViewById(R.id.chat_name);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
