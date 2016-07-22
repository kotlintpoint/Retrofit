package com.example.ankitsodha.retrofitdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ankitsodha.retrofitdemo.R;
import com.example.ankitsodha.retrofitdemo.model.User;

import java.util.List;

/**
 * Created by Ankit Sodha on 22-Jul-16.
 */
public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.DataViewHolder> {

    private List<User> users;
    private int rowLayout;
    private Context context;


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout moviesLayout;
        TextView username, password;
        //TextView data;
        //TextView movieDescription;
        //TextView rating;


        public DataViewHolder(View v) {
            super(v);
            username=(TextView)v.findViewById(R.id.username);
            password=(TextView)v.findViewById(R.id.password);
        }
    }

    public MyDataAdapter(List<User> users, int rowLayout, Context context) {
        this.users = users;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MyDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        holder.username.setText(users.get(position).getUsername());
        holder.password.setText(users.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
