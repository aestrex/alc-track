package com.codegod.alctrack.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codegod.alctrack.R;
import com.codegod.alctrack.activity.model.User;

import java.util.ArrayList;

/**
 * Created by alamzdayveed on 10/03/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<User> users;

    public DataAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(users.get(i).getLogin());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public ViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.name);

        }
    }

}
