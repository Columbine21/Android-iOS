package com.example.chapter3.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.friendViewHolder> {

    private List<String> nameList;
    private static final String TAG = "yuanziqi";

    public MyAdapter(List<String> friends_name_list) {
        nameList = friends_name_list;
    }


    @NonNull
    @Override
    public friendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.friend_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        friendViewHolder viewHolder = new friendViewHolder(view);
        Log.d(TAG, "onCreateViewHolder\n");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull friendViewHolder friendViewHolder, int position) {
        friendViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class friendViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        public friendViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.friend_number);
        }
        public void bind(int position){
            String friend_name = nameList.get(position);
            nameView.setText(friend_name);
        }
    }
}
